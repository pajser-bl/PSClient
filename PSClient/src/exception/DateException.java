package exception;

public class DateException extends Exception{

	private String error;
	
	public DateException(String error) {
		this.error = error;
	}
	
	public String toString() {
		return error;
	}
}
