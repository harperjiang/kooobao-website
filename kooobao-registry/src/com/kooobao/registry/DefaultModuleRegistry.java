package com.kooobao.registry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.kooobao.common.json.JsonWriter;

public class DefaultModuleRegistry implements ModuleRegistry {

	private Map<RegistryKey, List<RegistryCallbackStub>> registry = new ConcurrentHashMap<RegistryKey, List<RegistryCallbackStub>>();

	private Map<RegistryKey, ReadWriteLock> locks = new ConcurrentHashMap<RegistryKey, ReadWriteLock>();

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
			for (RegistryCallback callback : callbacks)
				callback.invoke(system, function, json);
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

}
