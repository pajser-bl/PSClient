package utility;

import java.util.Enumeration;
import java.util.ResourceBundle;
import client.ClientCommunication;
import client.User;
import javafx.stage.Stage;

public class ClientResources extends ResourceBundle{

	private Stage primaryWindow;
	private ClientCommunication clientCommunication;
	private User user;
	private double screenWidth;
	private double screenHeight;
	
	public ClientResources(Stage primaryWindow, ClientCommunication clientCommunication, User user, double width, double height) {
		this.primaryWindow = primaryWindow;
		this.clientCommunication = clientCommunication;
		this.user = user;
		this.screenWidth = width;
		this.screenHeight = height;
	}
	
	public Stage getStage() {
		return primaryWindow;
	}
	
	public void setStage(Stage stage) {
		primaryWindow = stage;
	}
	
	public ClientCommunication getClientCommunication() {
		return clientCommunication;
	}
	
	public void setClientCommunication(ClientCommunication clientCom) {
		clientCommunication = clientCom;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public double getScreenHeight() {
		return screenHeight;
	}
	
	public void setScreenWidth(double width) {
		this.screenHeight = width;
	}
	
	public double getScreenWidth() {
		return screenWidth;
	}
	
	public void setScreenHeight(double height) {
		this.screenHeight = height;
	}
	
	protected Object handleGetObject(String key) {
		return null;
	}

	public Enumeration<String> getKeys() {
		return null;
	}

}
