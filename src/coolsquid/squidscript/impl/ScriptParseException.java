package coolsquid.squidscript.impl;

public class ScriptParseException extends RuntimeException {

	private static final long serialVersionUID = 1519989508622886415L;

	public ScriptParseException() {
		super();
	}

	public ScriptParseException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ScriptParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ScriptParseException(String message) {
		super(message);
	}

	public ScriptParseException(Throwable cause) {
		super(cause);
	}
}