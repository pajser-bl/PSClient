package controller.fieldTechnician;

import client.ClientCommunication;
import javafx.stage.Stage;
import model.FieldTechnician;
import model.User;

public class InterventionHandler extends Thread {
	
	private Stage mainStage;
	private ClientCommunication clientComm;
	private FieldTechnician user;
	
	public InterventionHandler(Stage mainStage, ClientCommunication clientComm, FieldTechnician user) {
		this.mainStage = mainStage;
		this.clientComm = clientComm;
		this.user = user;
	}
	
	public void run() {
		int i = 0;
		while(i < 5)
			try {
				Thread.sleep(500);
				System.out.print("Testiranje");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public ClientCommunication getClientComm() {
		return clientComm;
	}

	public void setClientComm(ClientCommunication clientComm) {
		this.clientComm = clientComm;
	}

	public FieldTechnician getUser() {
		return user;
	}

	public void setUser(FieldTechnician user) {
		this.user = user;
	}
}
