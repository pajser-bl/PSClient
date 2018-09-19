package client;

import java.util.ArrayList;
import java.util.List;

public class RequestFunctionality {
	private ClientCommunication clientCommunication;
	
	public RequestFunctionality(ClientCommunication clientCommunication) {
		this.clientCommunication=clientCommunication;
	}
	
	
	public List<String> login(String username,String password){
		String requestType="LOGIN";
		ArrayList<String> list=new ArrayList<>();
		list.add(username);
		list.add(password);
		Request request=new Request(requestType, list);
		list.clear();
		list=clientCommunication.sendRequest(request);
		return list;
	}
}
