package coolsquid.squidscript.impl;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Primitives;

import coolsquid.squidscript.SquidClass;
import coolsquid.squidscript.SquidMethod;

public class SquidScriptProvider {

	private final Map<MethodInfo, Method> methods = Maps.newHashMap();

	public void registerClass(Class<?> class0) {
		try {
			SquidClass scriptClass = class0.getAnnotation(SquidClass.class);
			for (Method m: class0.getMethods())
				if (m.isAnnotationPresent(SquidMethod.class)) {
					Class<?>[] originalParameterTypes = m.getParameterTypes();
					Class<?>[] parameterTypes = new Class<?>[originalParameterTypes.length];
					for (int a = 0; a < originalParameterTypes.length; a++)
						parameterTypes[a] = Misc.isNumber(originalParameterTypes[a]) ? Number.class : originalParameterTypes[a];
					methods.put(new MethodInfo(scriptClass.value(), m.getName(), parameterTypes), m);
				}
		} catch (Throwable t) {
			Throwables.propagate(t);
		}
	}

	public void parse(String string) {
		if (!string.endsWith(";"))
			throw new ScriptParseException("Exception on line 1: missing semicolon");
		String[] lines = string.split(";");
		for (int a = 0; a < lines.length; a++) {
			try {
				String line = lines[a].split(" *# *")[a];
				String[] splitBrackets = line.split("\\(");
				String fullMethod = splitBrackets[0];
				String className = fullMethod.substring(0, fullMethod.lastIndexOf('.'));
				String methodName = fullMethod.substring(fullMethod.lastIndexOf('.') + 1);
				String unparsedParameters = splitBrackets[1].split("\\)")[0];
				List<Class<?>> parameterTypes = Lists.newArrayList();
				List<Object> parsedParameters = Lists.newArrayList();
				for (String parameter: unparsedParameters.split(" *, *")) {
					Object value;
					if (isNumber(parameter)) {
						value = parseNumber(parameter);
					}
					else if (isChar(parameter)) {
						value = parseChar(parameter);
					}
					else if (isBoolean(parameter)) {
						value = parseBoolean(parameter);
					}
					else {
						value = parameter.substring(1, parameter.length() - 1);
					}
					parsedParameters.add(value);
					parameterTypes.add(value.getClass());
				}
				Class<?>[] parameterTypeArray = new Class<?>[parameterTypes.size()];
				for (int b = 0; b < parameterTypes.size(); b++) {
					Class<?> c = parameterTypes.get(b);
					if (Number.class.isAssignableFrom(c)) {
						c = Number.class;
					}
					else if (Primitives.isWrapperType(c))
						c = Primitives.unwrap(c);
					parameterTypeArray[b] = c;
				}
				Method method = methods.get(new MethodInfo(className, methodName, parameterTypeArray));
				if (method == null) {
					System.out.println("debug: " + methods);
					throw new ScriptParseException("No such method: " + className + '.' + methodName + '(' + Misc.toString(parameterTypeArray) + ')');
				}
				List<Object> parameters = Lists.newArrayList();
				for (int b = 0; b < parameterTypes.size(); b++) {
					Object parameter = parsedParameters.get(b);
					if (parameter instanceof Number) {
						Class<?> c = method.getParameterTypes()[b];
						if (c == int.class)
							parameter = (int) parameter;
					}
					parameters.add(parameter);
				}
				method.invoke(null, parameters.toArray(new Object[0]));
			} catch (Throwable t) {
				throw new ScriptParseException("Exception on line " + (a + 1), t);
			}
		}
	}

	private char parseChar(String parameter) {
		return parameter.charAt(0);
	}

	private boolean isChar(String parameter) {
		return parameter.length() == 1 && !isNumber(parameter);
	}

	private Number parseNumber(String parameter) {
		if (parameter.matches("-{0,1}\\d*"))
			return Long.parseLong(parameter);
		else if (parameter.matches("-{0,1}\\d+\\.\\d*"))
			return Double.parseDouble(parameter);
		throw new NumberFormatException();
	}

	private boolean isNumber(String parameter) {
		return parameter.matches("-{0,1}\\d+\\.{0,1}\\d*");
	}

	private boolean parseBoolean(String parameter) {
		return Boolean.parseBoolean(parameter);
	}

	private boolean isBoolean(String parameter) {
		return parameter.matches("(true|false)");
	}
}