package com.kooobao.registry;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.RemoteAccessException;

import com.kooobao.common.json.JsonWriter;

public class DefaultRegistryService implements RegistryService {

	private Map<RegistryKey, List<RegistryCallbackStub>> registry = new ConcurrentHashMap<RegistryKey, List<RegistryCallbackStub>>();

	private Map<RegistryKey, ReadWriteLock> locks = new ConcurrentHashMap<RegistryKey, ReadWriteLock>();

	private Logger log = LoggerFactory.getLogger(getClass());

	protected ReadWriteLock getLock(RegistryKey key) {
		if (!locks.containsKey(key)) {
			synchronized (locks) {
				if (!locks.containsKey(key))
					locks.put(key, new ReentrantReadWriteLock());
			}
		}
		return locks.get(key);
	}

	@Override
	public void register(String system, String function, String callbackAddress) {
		log.info(MessageFormat.format("Registering {0} to {1}.{2}",
				callbackAddress, system, function));
		RegistryKey key = new RegistryKey(system, function);
		if (!registry.containsKey(key)) {
			synchronized (registry) {
				if (!registry.containsKey(key)) {
					registry.put(key, new ArrayList<RegistryCallbackStub>());
				}
			}
		}
		Lock write = getLock(key).writeLock();
		try {
			write.lock();
			registry.get(key).add(createCallback(callbackAddress));
		} finally {
			write.unlock();
		}

	}

	@Override
	public void unregister(String system, String function,
			String callbackAddress) {
		log.info(MessageFormat.format("Unregistering {0} from {1}.{2}",
				callbackAddress, system, function));
		RegistryKey key = new RegistryKey(system, function);
		if (registry.containsKey(key)) {
			Iterator<RegistryCallbackStub> callbacks = registry.get(key)
					.iterator();
			while (callbacks.hasNext()) {
				RegistryCallbackStub callback = callbacks.next();
				if (callback.getAddress().equals(callbackAddress)) {
					Lock write = getLock(key).writeLock();
					try {
						write.lock();
						callbacks.remove();
					} finally {
						write.unlock();
					}
				}
			}
		}

	}

	@Override
	public void publish(String system, String function, Object data) {
		log.info(MessageFormat
				.format("Publishing to {1}.{2}", system, function));
		RegistryKey key = new RegistryKey(system, function);
		if (registry.containsKey(key)) {
			Lock read = getLock(key).readLock();
			List<RegistryCallback> callbacks = new ArrayList<RegistryCallback>();
			try {
				read.lock();
				callbacks.addAll(registry.get(key));
			} finally {
				read.unlock();
			}
			String json = convert(data);
			List<RegistryCallback> failed = new ArrayList<RegistryCallback>();
			for (RegistryCallback callback : callbacks)
				try {
					callback.invoke(system, function, json);
				} catch (RemoteAccessException e) {
					// Remove from list
					log.error(MessageFormat.format(
							"Remote Access Error while accessing {0}.{1}",
							system, function), e);
					failed.add(callback);
				}
			if (failed.size() != 0) {
				try {
					read.lock();
					registry.get(key).removeAll(failed);
				} finally {
					read.unlock();
				}
			}
		}
	}

	protected RegistryCallbackStub createCallback(String callbackAddress) {
		return new RegistryCallbackStub(callbackAddress);
	}

	protected String convert(Object data) {
		JsonWriter writer = new JsonWriter();
		writer.write(data);
		return writer.toString();
	}

	private Set<String> systems = Collections
			.synchronizedSet(new HashSet<String>());

	@Override
	public void install(String system) {
		systems.add(system);
	}

	@Override
	public String[] list() {
		return systems.toArray(new String[systems.size()]);
	}

}
