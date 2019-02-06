package client;

import java.util.ArrayList;

import javafx.concurrent.Task;
import model.FieldTechnician;

public class TestThread extends Task {

	private ClientCommunication clientComm;
	private FieldTechnician user;
	private boolean running;

	public TestThread(ClientCommunication clientComm, FieldTechnician user, boolean running) {
		this.clientComm = clientComm;
		this.user = user;
		this.running = running;
	}

	@Override
	protected Object call() throws Exception {
		while (running) {
			Thread.sleep(5000);
			if(user.getState().equals("aktivan")) {
				user.setState("neaktivan");
			} else user.setState("aktivan");
			ArrayList<String> reply = clientComm.changeState(user.getId(), user.getState());
		}
		System.out.print("Task zavrsen");
		return null;
	}
}
