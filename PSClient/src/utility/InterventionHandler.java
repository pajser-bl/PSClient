package utility;

import java.util.ArrayList;

import client.ClientCommunication;
import controller.fieldTechnician.FieldTechnicianController;
import javafx.concurrent.Task;
import model.FieldTechnician;

public class InterventionHandler extends Task<Void> {

	private ClientCommunication clientComm;
	private FieldTechnician user;
	private FieldTechnicianController controller;
	
	public InterventionHandler(ClientCommunication clientComm, FieldTechnician user, FieldTechnicianController controller) {
		this.clientComm = clientComm;
		this.user = user;
		this.controller = controller;
	}

	protected Void call() throws Exception {
		int timer = 0;
		while(user.getState().equals("aktivan")) {
			System.out.println("Intervention thread");
			Thread.sleep(timer);
			ArrayList<String> reply = clientComm.checkOpenedIntervention(user.getId());
			if(reply.get(0).equals("CHECK FIELD TECHNICIAN INTERVENTION OK"))
				controller.checkOpenedIntervention(reply);
			timer = 10000;
		}
		return null;
	}
}
