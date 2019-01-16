package exception;

public class ServerReplyException extends Exception{
	
	private String message;
	
	public ServerReplyException(String message) {
		this.message = message;
	}
	
	public String toString() {
		return message;
	}
}
