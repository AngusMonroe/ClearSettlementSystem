package exception;

@SuppressWarnings("serial")
public class TimeOutOfRangeException extends RuntimeException{
	
	public TimeOutOfRangeException() {
		
	}
	
	public TimeOutOfRangeException(String message) {
		super(message);
	}
}
