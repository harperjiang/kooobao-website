package com.kooobao.registry;

import java.io.Serializable;

import org.apache.commons.lang.Validate;

public class RegistryKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3436060523146119190L;

	private String system;

	private String function;

	public RegistryKey(String system, String function) {
		Validate.notNull(system);
		Validate.notNull(function);
		this.system = system;
		this.function = function;
	}

	public String getSystem() {
		return system;
	}

	public String getFunction() {
		return function;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RegistryKey) {
			RegistryKey rk = (RegistryKey) obj;
			return getSystem().equals(rk.getSystem())
					&& getFunction().equals(rk.getFunction());
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return getSystem().hashCode() * 31 + getFunction().hashCode();
	}
}
