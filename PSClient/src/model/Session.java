package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Session {

	private ArrayList<Event> eventList;
	private String start;
	private String end;
	private String user;
	private String userId;
	private String sessionId;
	
	public Session() {
		sessionId = "0";
		eventList = new ArrayList<Event>();
	}
	
	public Session(String sessionId, String userId, String user, String start, String end) {
		this.sessionId = sessionId;
		this.userId = userId;
		this.user = user;
		this.start = start;
		this.end = end;
	}
	
	public Session(String sessionId, ArrayList<Event> eventList) {
			this.sessionId = sessionId;
			this.eventList = eventList;
			start = null;
			end = null;
	}
	
	public String toString() {
		String session = "";
		for(int i = 0; i < eventList.size(); i++)
			session += eventList.get(i).toString();
		return session;
	}
	
	public ArrayList<Event> getEventList(){
		return eventList;
	}
	
	public void setEventList(ArrayList<Event> eventList) {
		this.eventList = eventList;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
