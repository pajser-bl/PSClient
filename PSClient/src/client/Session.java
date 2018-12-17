package client;

import java.util.ArrayList;

public class Session {

	private String sessionId;
	private ArrayList<Event> eventList;
	
	public Session() {
		sessionId = "0";
		eventList = new ArrayList<Event>();
	}
	
	public Session(String sessionId, ArrayList<Event> eventList) {
			this.sessionId = sessionId;
			this.eventList = eventList;
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
}
