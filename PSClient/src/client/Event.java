package client;

import java.time.LocalDateTime;

public class Event {

	private LocalDateTime timeStamp;
	private String action;
	
	public Event(String action) {
		timeStamp = LocalDateTime.now();
		this.action = action;
	}
	
	public String toString() {
		return timeStamp.toString() + ": " + action + "\n";
	}
	
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
