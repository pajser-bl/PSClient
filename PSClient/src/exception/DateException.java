package exception;

public class DateException extends Exception{

	private String message;
	
	public DateException(String message) {
		this.message = message;
	}
	
	public String toString() {
		return message;
	}
}
