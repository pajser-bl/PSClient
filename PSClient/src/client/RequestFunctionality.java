package client;

import java.util.ArrayList;

public class RequestFunctionality {
	
	public static ArrayList<String> login(ClientCommunication clientCommunication, String username, String password) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(username);
		arguments.add(password);
		Request request = new Request("LOGIN", arguments);
		return clientCommunication.sendRequest(request);
	}

	public static ArrayList<String> logout(ClientCommunication clientCommunication, String user_ID) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add("" + user_ID);
		Request request = new Request("LOGOUT", arguments);
		return clientCommunication.sendRequest(request);
	}

	public static ArrayList<String> newUser(ClientCommunication clientCommunication, String name, String lastName, String dateOfBirth, String type,
											String qualification) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(name);
		arguments.add(lastName);
		arguments.add(dateOfBirth);
		arguments.add(type);
		arguments.add(qualification);
		Request request = new Request("NEW USER", arguments);
		return clientCommunication.sendRequest(request);
	}

	public static ArrayList<String> updateUser(ClientCommunication clientCommunication, String userID, String name, String lastName,
			 								   String dateOfBirth, String type, String qualification) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userID);
		arguments.add(name);
		arguments.add(lastName);
		arguments.add(dateOfBirth);
		arguments.add(type);
		arguments.add(qualification);
		Request request = new Request("UPDATE USER", arguments);
		return clientCommunication.sendRequest(request);
	}

	public static ArrayList<String> deleteUser(ClientCommunication clientCommunication, String userID) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userID);
		Request request = new Request("DELETE USER", arguments);
		return clientCommunication.sendRequest(request);
	}

	public static ArrayList<String> newCredentials(ClientCommunication clientCommunication, String userID, String username, String password) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userID);
		arguments.add(username);
		arguments.add(password);
		Request request = new Request("NEW CREDENTIALS", arguments);
		return clientCommunication.sendRequest(request);
	}

	public static ArrayList<String> updateCredentials(ClientCommunication clientCommunication, String userID, String username, String password) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userID);
		arguments.add(username);
		arguments.add(password);
		Request request = new Request("UPDATE PASSWORD", arguments);
		return clientCommunication.sendRequest(request);
	}

	public static ArrayList<String> deleteCredentials(ClientCommunication clientCommunication, String userID) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userID);
		Request request = new Request("DELETE CREDENTIALS", arguments);
		return clientCommunication.sendRequest(request);
	}

	public static ArrayList<String> viewClient(ClientCommunication clientCommunication, String client_ID) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_ID);
		Request request = new Request("VIEW CLIENT", arguments);
		return clientCommunication.sendRequest(request);
	}

	public static ArrayList<String> newClient(ClientCommunication clientCommunication, String name, String lastName,String phone_number) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(name);
		arguments.add(lastName);
		arguments.add(phone_number);
		Request request = new Request("NEW CLIENT", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> updateClient(ClientCommunication clientCommunication, String client_ID, String name, String lastName,
												 String phone_number){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_ID);
		arguments.add(name);
		arguments.add(lastName);
		arguments.add(phone_number);
		Request request = new Request("UPDATE CLIENT", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> deleteClient(ClientCommunication clientCommunication, String client_ID){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_ID);
		Request request = new Request("DELETE CLIENT", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> viewIntervention(ClientCommunication clientCommunication, String intervention_ID){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(intervention_ID);
		Request request = new Request("VIEW INTERVENTION",arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> newIntervention(ClientCommunication clientCommunication, String id_client, String id_vehicle,
													String id_user_opened, String opened_on ){
		ArrayList<String> arguments=new ArrayList<>();
		arguments.add(id_client);
		arguments.add(id_vehicle);
		arguments.add(id_user_opened);
		arguments.add(opened_on);
		Request request= new Request ("NEW INTERVENTION",arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> updateIntervention(ClientCommunication clientCommunication, String id_intervention, String id_client, 
													   String id_vehicle, String id_user_opened, String id_user_closed, String opened_on,
													   String closed_on, String remark, String closed){
		ArrayList<String> arguments=new ArrayList<>();
		arguments.add(id_intervention);
		arguments.add(id_client);
		arguments.add(id_vehicle);
		arguments.add(id_user_opened);
		arguments.add(id_user_closed);
		arguments.add(opened_on);
		arguments.add(closed_on);
		arguments.add(remark);
		arguments.add(closed);
		Request request= new Request ("UPDATE INTERVENTION",arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> deleteIntervention(ClientCommunication clientCommunication, String intervention_ID){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(intervention_ID);
		Request request = new Request("DELETE INTERVENTION", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> closeIntervention(ClientCommunication clientCommunication, String intervention_ID, String ID_closed,
											   		  String closed_on, String remark, String closed){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(intervention_ID);
		arguments.add(ID_closed);
		arguments.add(closed_on);
		arguments.add(remark);
		arguments.add(closed);
		Request request = new Request("CLOSE INTERVENTION", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> viewSubscription(ClientCommunication clientCommunication, String subscription_ID) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(subscription_ID);
		Request request = new Request("VIEW SUBSCRIPTION", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> newSubscription(ClientCommunication clientCommunication, String client_ID, String start_date, String end_date) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_ID);
		arguments.add(start_date);
		arguments.add(end_date);
		Request request = new Request("NEW SUBSCRIPTION", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> updateSubscription(ClientCommunication clientCommunication, String subscription_ID, String client_ID,
													   String start_date, String end_date) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(subscription_ID);
		arguments.add(client_ID);
		arguments.add(start_date);
		arguments.add(end_date);
		Request request = new Request("UPDATE SUBSCRIPTION", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> deleteSubscription(ClientCommunication clientCommunication, String subscription_ID) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(subscription_ID);
		Request request = new Request("DELETE SUBSCRIPTION", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> newFieldReport(ClientCommunication clientCommunication, String intervention_ID, String user_ID,
												   String assistance, String time,  String remark) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(intervention_ID);
		arguments.add(user_ID);
		arguments.add(assistance);
		arguments.add(time);
		arguments.add(remark);
		Request request = new Request("NEW ROADREPORT", arguments);
		return clientCommunication.sendRequest(request);
	}
}
