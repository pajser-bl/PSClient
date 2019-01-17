package client;

public class FieldTechnician {

	private String Id;
	private String name;
	private String lastName;
	private String state;
	
	public FieldTechnician(String Id, String name, String lastName, String state) {
		this.Id = Id;
		this.name = name;
		this.lastName = lastName;
		this.state = state;
	}
	
	public String toStringNoState() {
		return Id + ": " + name + " " + lastName;
	}
	
	public String toString() {
		return Id + ": " + name + " " + lastName + ": " + state;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
