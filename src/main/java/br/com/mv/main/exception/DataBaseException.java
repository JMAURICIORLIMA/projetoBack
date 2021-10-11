package br.com.mv.main.exception;

public class DataBaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DataBaseException(String message) {
		super(message);
	}
	
	public DataBaseException(String message, Throwable causa) {
		super(message, causa);
	}
}
