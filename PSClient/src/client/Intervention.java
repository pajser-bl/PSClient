package client;

import java.time.LocalDateTime;

public class Intervention {

	private String state;
	private LocalDateTime openedOn;
	private LocalDateTime closedOn;
	private LocalDateTime reportTime;
	private LocalDateTime serviceTime;
	private String id;
	private String client;
	private String vehicleModel;
	private String vehicleManu;
	private String vehicleYear;
	private String vehicleLicencePlace;
	private String userOpened;
	private String userClosed;
	private String service;
	private String operaterReport;
	private String fieldTechnician;
	private String fieldReport;
	private String supervisor;
	private String supervisorReport;
	
	public Intervention(String id, String clientId, String userOpenedId, String userClosedId, LocalDateTime openedOn,
			LocalDateTime closedOn, String state) {
		this.id = id;
		this.client = clientId;
		this.userOpened = userOpenedId;
		this.userClosed = userClosedId;
		this.openedOn = openedOn;
		this.closedOn = closedOn;
		this.state = state;
	}
	
	public Intervention(String client, String userOpenedId) {
		id = "0";
		this.client = client;
		this.userOpened = userOpenedId;
		openedOn = LocalDateTime.now();
		state = "Otvorena";
	}
	
	public Intervention(String id, String client, String userId, LocalDateTime openedOn, String fieldTechnician, String state) {
		this.id = id;
		this.client = client;
		this.userOpened = userId;
		this.openedOn = openedOn;
		this.fieldTechnician = fieldTechnician;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getUserOpened() {
		return userOpened;
	}

	public void setUserOpened(String userOpened) {
		this.userOpened = userOpened;
	}

	public String getUserClosed() {
		return userClosed;
	}

	public void setUserClosedId(String userClosed) {
		this.userClosed = userClosed;
	}

	public LocalDateTime getOpenedOn() {
		return openedOn;
	}

	public void setOpenedOn(LocalDateTime openedOn) {
		this.openedOn = openedOn;
	}

	public LocalDateTime getClosedOn() {
		return closedOn;
	}

	public String getFieldTechnician() {
		return fieldTechnician;
	}

	public void setFieldTechnician(String fieldTechnician) {
		this.fieldTechnician = fieldTechnician;
	}

	public void setUserClosed(String userClosed) {
		this.userClosed = userClosed;
	}

	public void setClosedOn(LocalDateTime closedOn) {
		this.closedOn = closedOn;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOperaterReport() {
		return operaterReport;
	}

	public void setOperaterReport(String operaterReport) {
		this.operaterReport = operaterReport;
	}

	public String getFieldReport() {
		return fieldReport;
	}

	public void setFieldReport(String fieldReport) {
		this.fieldReport = fieldReport;
	}

	public String getSupervisorReport() {
		return supervisorReport;
	}

	public void setSupervisorReport(String supervisorReport) {
		this.supervisorReport = supervisorReport;
	}
}
