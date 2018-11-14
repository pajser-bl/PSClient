package client;

public class User {

	private String userId;
	private String name;
	private String lastName;
	private String type;
	
	public User(String userId, String name, String lastName, String type){
		this.userId = userId;
		this.name = name;
		this.lastName = lastName;
		this.type = type;
	}
	
	public String toString() {
		return userId + " " + name + " " + lastName + " " + type;
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
}
