package model;

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
	private String vehicleLicencePlate;
	private String userOpened;
	private String userClosed;
	private String service;
	private String operaterReport;
	private String fieldTechnician;
	private String fieldReport;
	private String supervisor;
	private String supervisorReport;
	
	public Intervention(String id, String client, String vehicleLicencePlate, String vehicleModel, String vehicleManu, String vehicleYear,
	String userOpened, String fieldTechnician, LocalDateTime openedOn, String state, String service, LocalDateTime serviceTime,
	String fieldReport, String userClosed, LocalDateTime closedOn, String operaterReport, String supervisor, String supervisorReport,
	LocalDateTime reportTime) {
		this.state = state;
		this.openedOn = openedOn;
		this.closedOn = closedOn;
		this.reportTime = reportTime;
		this.serviceTime = serviceTime;
		this.id = id;
		this.client = client;
		this.vehicleModel = vehicleModel;
		this.vehicleManu = vehicleManu;
		this.vehicleYear = vehicleYear;
		this.vehicleLicencePlate = vehicleLicencePlate;
		this.userOpened = userOpened;
		this.userClosed = userClosed;
		this.service = service;
		this.operaterReport = operaterReport;
		this.fieldTechnician = fieldTechnician;
		this.fieldReport = fieldReport;
		this.supervisor = supervisor;
		this.supervisorReport = supervisorReport;
	}
	
	public Intervention(String id, String client, String userId, LocalDateTime openedOn, String fieldTechnician, String state,
			String service, LocalDateTime serviceTime, String fieldReport) {
		this.id = id;
		this.client = client;
		this.userOpened = userId;
		this.openedOn = openedOn;
		this.fieldTechnician = fieldTechnician;
		this.state = state;
		this.service = service;
		this.serviceTime = serviceTime;
		this.fieldReport = fieldReport;
	}
	
	public Intervention(String id, String client, String userId, LocalDateTime openedOn, String fieldTechnician, String state) {
		this.id = id;
		this.client = client;
		this.userOpened = userId;
		this.openedOn = openedOn;
		this.fieldTechnician = fieldTechnician;
		this.state = state;
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

	public LocalDateTime getReportTime() {
		return reportTime;
	}

	public void setReportTime(LocalDateTime reportTime) {
		this.reportTime = reportTime;
	}

	public LocalDateTime getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(LocalDateTime serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleManu() {
		return vehicleManu;
	}

	public void setVehicleManu(String vehicleManu) {
		this.vehicleManu = vehicleManu;
	}

	public String getVehicleYear() {
		return vehicleYear;
	}

	public void setVehicleYear(String vehicleYear) {
		this.vehicleYear = vehicleYear;
	}

	public String getVehicleLicencePlate() {
		return vehicleLicencePlate;
	}

	public void setVehicleLicencePlate(String vehicleLicencePlate) {
		this.vehicleLicencePlate = vehicleLicencePlate;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
}
