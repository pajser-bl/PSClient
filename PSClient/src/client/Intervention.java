package client;

import java.time.LocalDateTime;

public class Intervention {

	private String id;
	private String client;
	private String vehicleId;
	private String userOpened;
	private String userClosed;
	private String fieldTechnician;
	private LocalDateTime openedOn;
	private LocalDateTime closedOn;
	private boolean closed;
	private String remark;
	
	public Intervention(String client, String vehicleId, String userOpenedId) {
		id = "0";
		this.client = client;
		this.vehicleId = vehicleId;
		this.userOpened = userOpenedId;
		openedOn = LocalDateTime.now();
		closed = false;
	}
	
	public Intervention(String id, String client, String userId, LocalDateTime openedOn, String fieldTechnician) {
		this.id = id;
		this.client = client;
		this.userOpened = userId;
		this.openedOn = openedOn;
		this.fieldTechnician = fieldTechnician;
	}
	
	public Intervention(String id, String clientId, String vehicleId, String userOpenedId, String userClosedId, LocalDateTime openedOn,
			LocalDateTime closedOn, boolean closed, String remark) {
		this.id = id;
		this.client = clientId;
		this.vehicleId = vehicleId;
		this.userOpened = userOpenedId;
		this.userClosed = userClosedId;
		this.openedOn = openedOn;
		this.closedOn = closedOn;
		this.closed = closed;
		this.remark = remark;
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

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
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

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
