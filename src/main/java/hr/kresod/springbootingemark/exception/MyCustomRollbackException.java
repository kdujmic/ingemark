package hr.kresod.springbootingemark.exception;

public class MyCustomRollbackException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public MyCustomRollbackException(String message) {
		super(message);
	}
	
}
