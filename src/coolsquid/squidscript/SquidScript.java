package coolsquid.squidscript;

import coolsquid.squidscript.impl.SquidScriptProvider;

public class SquidScript {

	private static final SquidScriptProvider provider = new SquidScriptProvider();

	public static void registerClass(Class<?> class0) {
		provider.registerClass(class0);
	}

	public static void parse(String string) {
		provider.parse(string);
	}
}