package client;

public class User {

	private String userId;
	private String name;
	private String lastName;
	private String type;
	private String userName;
	
	public User(String userId, String name, String lastName, String type, String userName){
		this.userId = userId;
		this.name = name;
		this.lastName = lastName;
		this.type = type;
		this.userName = userName;
	}
	
	public String toString() {
		return userId + " " + name + " " + lastName + " " + userName + " " + type;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
