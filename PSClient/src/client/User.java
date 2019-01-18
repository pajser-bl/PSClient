package client;

import java.time.LocalDate;

public class User {

	private String userId;
	private String name;
	private String lastName;
	private String type;
	private String username;
	private String qualification;
	private LocalDate date;
	
	public User(String userId, String name, String lastName, String type, String username, String qualification, LocalDate date){
		this.userId = userId;
		this.name = name;
		this.lastName = lastName;
		this.type = type;
		this.username = username;
		this.qualification = qualification;
		this.date = date;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getFieldTechnician() {
		return userId + " " + name + " " + lastName;
	}
	
	public String toString() {
		return userId + " " + name + " " + lastName + " " + username + " " + type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
