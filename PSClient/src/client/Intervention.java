package client;

import java.time.LocalDateTime;

public class Intervention {

	private String id;
	private String clientId;
	private String vehicleId;
	private String userOpenedId;
	private String userClosedId;
	private LocalDateTime openedOn;
	private LocalDateTime closedOn;
	private boolean closed;
	private String remark;
	
	public Intervention(String clientId, String vehicleId, String userOpenedId) {
		id = "0";
		this.clientId = clientId;
		this.vehicleId = vehicleId;
		this.userOpenedId = userOpenedId;
		openedOn = LocalDateTime.now();
		closed = false;
	}
	
	public Intervention(String id, String clientId, String vehicleId, String userOpenedId, String userClosedId, LocalDateTime openedOn,
			LocalDateTime closedOn, boolean closed, String remark) {
		this.id = id;
		this.clientId = clientId;
		this.vehicleId = vehicleId;
		this.userOpenedId = userOpenedId;
		this.userClosedId = userClosedId;
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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getUserOpenedId() {
		return userOpenedId;
	}

	public void setUserOpenedId(String userOpenedId) {
		this.userOpenedId = userOpenedId;
	}

	public String getUserClosedId() {
		return userClosedId;
	}

	public void setUserClosedId(String userClosedId) {
		this.userClosedId = userClosedId;
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
