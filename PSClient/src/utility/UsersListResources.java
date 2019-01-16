package utility;

import java.util.ArrayList;
import java.util.Enumeration;
import client.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsersListResources extends ClientResources {
	
	private ArrayList<User> users;
	
	public UsersListResources(ClientResources resources, ArrayList<User> users) {
		super(resources.getStage(), resources.getClientCommunication(), resources.getUser(), resources.getScreenHeight(), 
				resources.getScreenHeight());
		this.users = users;
	}
	
	public ObservableList<User> getObservableUsers() {
		return FXCollections.observableArrayList(users);
	}
	
	public ArrayList<User> getUsers(){
		return users;
	}
	
	public String getFieldTechnician(int userIndex) {
		return users.get(userIndex).getFieldTechnician();
	}
	
	protected Object handleGetObject(String key) {
		return null;
	}

	public Enumeration<String> getKeys() {
		return null;
	}

}
