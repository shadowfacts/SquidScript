package coolsquid.squidscript.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MethodContainer {

	private final Object owner;
	private final Method method;

	public MethodContainer(Object owner, Method method) {
		this.owner = owner;
		this.method = method;
	}

	public Object invoke(Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return method.invoke(owner, args);
	}
}