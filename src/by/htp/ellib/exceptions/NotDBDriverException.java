package by.htp.ellib.exceptions;

public class NotDBDriverException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public NotDBDriverException() {
		super();
	}
	
	public NotDBDriverException(String message) {
		super (message);
				
	}
	
	public NotDBDriverException(Exception e) {
		super(e);
		
	}
	public NotDBDriverException(String message, Exception e) {
		super(message, e);
		
	}
	
}
