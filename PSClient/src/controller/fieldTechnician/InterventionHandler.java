package controller.fieldTechnician;

import java.util.ArrayList;

import utility.ClientResources;

public class InterventionHandler extends Thread {

	private ClientResources resources;

	public InterventionHandler(ClientResources resources) {
		super();
	}

	public void run() {
		while (true) {
			ArrayList<String> reply = resources.getClientCommunication().checkIntervention(resources.getUser().getUserId());
			if (reply.get(0).equals("CHECK FIELD TECHNICIAN INTERVENTION OK")) {
				System.out.println("Ima intervencija");
			} else
				System.out.println("Nema intervencija");
			try {
				Thread.sleep(5000);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
