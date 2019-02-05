package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {

	private LocalDateTime timeStamp;
	private String action;
	
	public Event(String action) {
		timeStamp = LocalDateTime.now();
		this.action = action;
	}
	
	public String toString() {
		return timeStamp.format(DateTimeFormatter.ISO_DATE) + " " + timeStamp.format(DateTimeFormatter.ISO_TIME) + ": " + action + "\n";
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
