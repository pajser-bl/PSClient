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

	public static void logout(ClientCommunication clientCommunication, String user_Id) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(user_Id);
		Request request = new Request("LOGOUT", arguments);
		clientCommunication.logout(request);
	}

	public static ArrayList<String> newUser(ClientCommunication clientCommunication, String name, String lastName, String dateOfBirth, String type,
											String qualification, String userName, String password) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(name);
		arguments.add(lastName);
		arguments.add(dateOfBirth);
		arguments.add(type);
		arguments.add(qualification);
		arguments.add(userName);
		arguments.add(password);
		Request request = new Request("NEW USER", arguments);
		return clientCommunication.sendRequest(request);
	}

	public static ArrayList<String> updateUser(ClientCommunication clientCommunication, String userId, String name, String lastName,
			 								   String dateOfBirth, String type, String qualification) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userId);
		arguments.add(name);
		arguments.add(lastName);
		arguments.add(dateOfBirth);
		arguments.add(type);
		arguments.add(qualification);
		Request request = new Request("UPDATE USER", arguments);
		return clientCommunication.sendRequest(request);
	}

	public static ArrayList<String> deleteUser(ClientCommunication clientCommunication, String userId) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userId);
		Request request = new Request("DELETE USER", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> getUsers(ClientCommunication clientCommunication, String userId){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userId);
		Request request = new Request("VIEW USERS", arguments);
		return clientCommunication.sendRequest(request);
	}

	public static ArrayList<String> updateCredentials(ClientCommunication clientCommunication, String userId, String username, String password) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userId);
		arguments.add(username);
		arguments.add(password);
		Request request = new Request("UPDATE PASSWORD", arguments);
		return clientCommunication.sendRequest(request);
	}

	public static ArrayList<String> viewClient(ClientCommunication clientCommunication, String client_Id) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_Id);
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
	
	public static ArrayList<String> updateClient(ClientCommunication clientCommunication, String client_Id, String name, String lastName,
												 String phone_number){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_Id);
		arguments.add(name);
		arguments.add(lastName);
		arguments.add(phone_number);
		Request request = new Request("UPDATE CLIENT", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> deleteClient(ClientCommunication clientCommunication, String client_Id){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_Id);
		Request request = new Request("DELETE CLIENT", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> viewIntervention(ClientCommunication clientCommunication, String intervention_Id){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(intervention_Id);
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
	
	public static ArrayList<String> deleteIntervention(ClientCommunication clientCommunication, String intervention_Id){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(intervention_Id);
		Request request = new Request("DELETE INTERVENTION", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> closeIntervention(ClientCommunication clientCommunication, String intervention_Id, String Id_closed,
											   		  String closed_on, String remark, String closed){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(intervention_Id);
		arguments.add(Id_closed);
		arguments.add(closed_on);
		arguments.add(remark);
		arguments.add(closed);
		Request request = new Request("CLOSE INTERVENTION", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> viewSubscription(ClientCommunication clientCommunication, String subscription_Id) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(subscription_Id);
		Request request = new Request("VIEW SUBSCRIPTION", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> newSubscription(ClientCommunication clientCommunication, String client_Id, String start_date, String end_date) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_Id);
		arguments.add(start_date);
		arguments.add(end_date);
		Request request = new Request("NEW SUBSCRIPTION", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> updateSubscription(ClientCommunication clientCommunication, String subscription_Id, String client_Id,
													   String start_date, String end_date) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(subscription_Id);
		arguments.add(client_Id);
		arguments.add(start_date);
		arguments.add(end_date);
		Request request = new Request("UPDATE SUBSCRIPTION", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> deleteSubscription(ClientCommunication clientCommunication, String subscription_Id) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(subscription_Id);
		Request request = new Request("DELETE SUBSCRIPTION", arguments);
		return clientCommunication.sendRequest(request);
	}
	
	public static ArrayList<String> newFieldReport(ClientCommunication clientCommunication, String intervention_Id, String user_Id,
												   String assistance, String time,  String remark) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(intervention_Id);
		arguments.add(user_Id);
		arguments.add(assistance);
		arguments.add(time);
		arguments.add(remark);
		Request request = new Request("NEW ROADREPORT", arguments);
		return clientCommunication.sendRequest(request);
	}
}
