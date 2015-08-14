package coolsquid.squidscript.impl;

import java.util.Arrays;

class MethodInfo {

	private final String owner, name;
	private final Class<?>[] parameterTypes;

	public MethodInfo(String owner, String name, Class<?>... parameterTypes) {
		this.owner = owner;
		this.name = name;
		this.parameterTypes = parameterTypes;
	}

	public String getOwner() {
		return owner;
	}

	public String getName() {
		return name;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + Arrays.hashCode(parameterTypes);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MethodInfo other = (MethodInfo) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		}
		else
			if (!name.equals(other.name))
				return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		}
		else
			if (!owner.equals(other.owner))
				return false;
		if (!Arrays.equals(parameterTypes, other.parameterTypes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MethodInfo [owner=" + owner + ", name=" + name
				+ ", parameterTypes=" + Arrays.toString(parameterTypes) + "]";
	}
}