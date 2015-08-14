package coolsquid.squidscript.impl;

class Misc {

	public static String toString(Class<?>[] array) {
		StringBuilder b = new StringBuilder();
		for (Class<?> e: array)
			if (e != null)
				b.append(e.getName()).append(", ");
		return b.substring(0, b.length() - 2);
	}

	public static boolean isNumber(Class<?> class1) {
		return Number.class.isAssignableFrom(class1) || class1 == byte.class || class1 == short.class || class1 == int.class || class1 == double.class || class1 == float.class || class1 == double.class;
	}
}