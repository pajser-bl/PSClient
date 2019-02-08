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
	
	public Session(String user) {
		this.user = user;
		sessionId = "0";
		eventList = new ArrayList<>();
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
	
	public void userLogged() {
		eventList.add(new Event("Korisnik " + user + " se prijavio na sistem"));
	}
	
	public void changeState(String state) {
		eventList.add(new Event("Korisnik " + user + " je promjenio stanje u " + state));
	}
	
	public void receiveIntervention(String interventionId) {
		eventList.add(new Event("Korisnik " + user + " je dobio novu intervenciju(Id:" + interventionId + ")"));
	}
	
	public void newIntervention() {
		eventList.add(new Event("Korisnik " + user + " je otvorio novu intervenciju"));
	}
	
	public void closeIntervention(String interventionId) {
		eventList.add(new Event("Korisnik " + user + " je zatvorio intervenciju(Id:" + interventionId + ")"));
	}
	
	public void newFieldReport(String interventionId) {
		eventList.add(new Event("Korisnik " + user + " je poslao ternski izvjestaj za intervenciju(Id:" + interventionId + ")"));
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
