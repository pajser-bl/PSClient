package client;

import java.util.ArrayList;

public class RequestFunctionality {
	private ClientCommunication clientCommunication;

	public RequestFunctionality(ClientCommunication clientCommunication) {
		this.clientCommunication = clientCommunication;
	}

	public ArrayList<String> login(String username, String password) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(username);
		arguments.add(password);
		Request request = new Request("LOGIN", arguments);
		return clientCommunication.sendRequest(request);
	}

	public ArrayList<String> logout() {
		Request request = new Request("LOGOUT", null);
		return clientCommunication.sendRequest(request);
	}

	public ArrayList<String> newUser(String name, String surname, String dateOfBirth, String type,
			String qualification) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(name);
		arguments.add(surname);
		arguments.add(dateOfBirth);
		arguments.add(type);
		arguments.add(qualification);
		Request request = new Request("NEW USER", arguments);
		return clientCommunication.sendRequest(request);
	}

	public ArrayList<String> updateUser(String userID, String name, String surname, String dateOfBirth, String type,
			String qualification) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userID);
		arguments.add(name);
		arguments.add(surname);
		arguments.add(dateOfBirth);
		arguments.add(type);
		arguments.add(qualification);
		Request request = new Request("UPDATE USER", arguments);
		return clientCommunication.sendRequest(request);
	}

	public ArrayList<String> deleteUser(String userID) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userID);
		Request request = new Request("DELETE USER", arguments);
		return clientCommunication.sendRequest(request);
	}

	public ArrayList<String> newCredentials(String userID, String username, String password) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userID);
		arguments.add(username);
		arguments.add(password);
		Request request = new Request("NEW CREDENTIALS", arguments);
		return clientCommunication.sendRequest(request);
	}

	public ArrayList<String> updateCredentials(String userID, String username, String password) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userID);
		arguments.add(username);
		arguments.add(password);
		Request request = new Request("UPDATE PASSWORD", arguments);
		return clientCommunication.sendRequest(request);
	}

	public ArrayList<String> deleteCredentials(String userID) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userID);
		Request request = new Request("DELETE CREDENTIALS", arguments);
		return clientCommunication.sendRequest(request);
	}

	public ArrayList<String> viewClient(String client_ID) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_ID);
		Request request = new Request("VIEW CLIENT", arguments);
		return clientCommunication.sendRequest(request);
	}

	public ArrayList<String> newClient(String name, String surname,String phone_number) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(name);
		arguments.add(surname);
		arguments.add(phone_number);
		Request request = new Request("NEW CLIENT", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public ArrayList<String> updateClient(String client_ID, String name, String surname, String phone_number){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_ID);
		arguments.add(name);
		arguments.add(surname);
		arguments.add(phone_number);
		Request request = new Request("UPDATE CLIENT", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public ArrayList<String> deleteClient(String client_ID){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_ID);
		Request request = new Request("DELETE CLIENT", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	
	

	public ArrayList<String> viewIntervention(String intervention_ID){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(intervention_ID);
		Request request = new Request("VIEW INTERVENTION",arguments);
		return clientCommunication.sendRequest(request);
	}
	public ArrayList<String> newIntervention(String id_client, String id_vehicle, String id_user_opened, String opened_on ){
		ArrayList<String> arguments=new ArrayList<>();
		arguments.add(id_client);
		arguments.add(id_vehicle);
		arguments.add(id_user_opened);
		arguments.add(opened_on);
		Request request= new Request ("NEW INTERVENTION",arguments);
		return clientCommunication.sendRequest(request);
	}
}
