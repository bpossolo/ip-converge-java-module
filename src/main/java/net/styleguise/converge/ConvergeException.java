package net.styleguise.converge;

@SuppressWarnings("serial")
public class ConvergeException extends RuntimeException {
	
	public ConvergeException(String message) {
		super(message);
	}
	
	public ConvergeException(String message, Throwable cause) {
		super(message, cause);
	}

}
