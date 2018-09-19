package client;

import java.util.ArrayList;
import java.util.List;

public class RequestFunctionality {
	private ClientCommunication clientCommunication;
	
	public RequestFunctionality(ClientCommunication clientCommunication) {
		this.clientCommunication=clientCommunication;
	}
	
	public ArrayList<String> login(String username,String password){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(username);
		arguments.add(password);
		Request request = new Request("LOGIN", arguments);
		return clientCommunication.sendRequest(request);
	}
}
