package utility;

import java.util.ArrayList;
import java.util.Enumeration;

import client.ClientCommunication;
import client.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class AdministratorResources extends ClientResources {
	
	private ArrayList<User> users;
	private Boolean userUpdate;
	
	public AdministratorResources(Stage primaryWindow, ClientCommunication clientCommunication, User user, double width, double height,
			ArrayList<User> users) {
		super(primaryWindow, clientCommunication, user, width, height);
		this.users = users;
		userUpdate = false;
	}
	
	public AdministratorResources(ClientResources resources, ArrayList<User> users) {
		super(resources.getStage(), resources.getClientCommunication(), resources.getUser(), resources.getScreenHeight(), 
				resources.getScreenHeight());
		this.users = users;
		userUpdate = false;
	}
	
	public Boolean getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(Boolean userUpdate) {
		this.userUpdate = userUpdate;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ObservableList<User> getObservableUsers() {
		return FXCollections.observableArrayList(users);
	}
	
	public ArrayList<User> getUsers(){
		return users;
	}

	protected Object handleGetObject(String key) {
		return null;
	}

	public Enumeration<String> getKeys() {
		return null;
	}

}
