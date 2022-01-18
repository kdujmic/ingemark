package hr.kresod.springbooteingemark.exceptions;

public class MyCustomRollbackException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2808767731356751027L;
	private String message;
	
	
	public MyCustomRollbackException(String message) {
		this.message = message;	
		
	}

	
	@Override
	public String toString() {

		return this.message;
	}
}
