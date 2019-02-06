package client;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import exception.ConnectionTimeoutException;
import model.Request;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import utility.MessageBox;

public class ClientCommunication {
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public ClientCommunication(String ip, int port) throws ConnectionTimeoutException {
		socket = new Socket();
		try {
			socket.connect(new InetSocketAddress(ip, port), 5000);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		} catch (IOException e) {
			throw new ConnectionTimeoutException();
		}
	}
	
	public ArrayList<String> sendRequest(Request request) {
		output.println(new Gson().toJson(request));
		try {
			return new Gson().fromJson(input.readLine(), new TypeToken<ArrayList<String>>(){}.getType());
		}
		catch(Exception e) {
			MessageBox.displayMessage("Greska!", "Server nije odgovorio!");
			return null;
		}
	}
	
	public void closeConnection() {
		try {
			socket.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> login(String username, String password) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(username);
		arguments.add(password);
		Request request = new Request("LOGIN", arguments);
		return sendRequest(request);
	}

	public void logout(String user_Id) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(user_Id);
		Request request = new Request("LOGOUT", arguments);
		output.println(new Gson().toJson(request));
	}

	public ArrayList<String> newUser(String name, String lastName, String dateOfBirth, String type,
									 String qualification, String licence, String userName, String password) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(name);
		arguments.add(lastName);
		arguments.add(dateOfBirth);
		arguments.add(type);
		arguments.add(qualification);
		arguments.add(licence);
		arguments.add(userName);
		arguments.add(password);
		Request request = new Request("NEW USER", arguments);
		return sendRequest(request);
	}

	public ArrayList<String> updateUser(String userId, String name, String lastName,
			 						 	String dateOfBirth, String type, String qualification) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userId);
		arguments.add(name);
		arguments.add(lastName);
		arguments.add(dateOfBirth);
		arguments.add(type);
		arguments.add(qualification);
		Request request = new Request("UPDATE USER", arguments);
		return sendRequest(request);
	}

	public ArrayList<String> deleteUser(String userId) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userId);
		Request request = new Request("DELETE USER", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> getUser(String userId){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userId);
		Request request = new Request("VIEW USER", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> getUsers(String userId){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userId);
		Request request = new Request("VIEW USERS", arguments);
		return sendRequest(request);
	}

	public ArrayList<String> changePassword(String userId, String password) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userId);
		arguments.add(password);
		Request request = new Request("CHANGE PASSWORD", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> viewSessions(String userId){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userId);
		Request request = new Request("VIEW SESSIONS", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> viewIntervention(String intervention_Id){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(intervention_Id);
		Request request = new Request("VIEW INTERVENTION",arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> newIntervention(String id_client, String id_vehicle, String id_user_opened, String opened_on ){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(id_client);
		arguments.add(id_vehicle);
		arguments.add(id_user_opened);
		arguments.add(opened_on);
		Request request= new Request("NEW INTERVENTION",arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> showOpenedInterventions(){
		ArrayList<String> arguments = new ArrayList<>();
		Request request = new Request("VIEW OPENED INTERVENTIONS", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> updateIntervention(String id_intervention, String id_client, String id_vehicle, String id_user_opened, 
												String id_user_closed, String opened_on, String closed_on, String remark, String closed){
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
		return sendRequest(request);
	}
	
	public ArrayList<String> deleteIntervention(String intervention_Id){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(intervention_Id);
		Request request = new Request("DELETE INTERVENTION", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> closeIntervention(String intervention_Id, String Id_closed, String closed_on,
											   String remark, String closed){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(intervention_Id);
		arguments.add(Id_closed);
		arguments.add(closed_on);
		arguments.add(remark);
		arguments.add(closed);
		Request request = new Request("CLOSE INTERVENTION", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> viewOpenedIntervention(String interventionId){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(interventionId);
		Request request = new Request("VIEW OPENED INTERVENTION", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> getAvailableFieldTechnicians() {
		ArrayList<String> arguments = new ArrayList<>();
		Request request = new Request("VIEW AVAILABLE FIELD TECHNICIANS", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> newRoadReport(String interventionId, String service, LocalDateTime time,  String report) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(interventionId);
		arguments.add(service);
		arguments.add(time.toString());
		arguments.add(report);
		Request request = new Request("NEW ROADREPORT", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> checkIntervention(String userId) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userId);
		Request request = new Request("CHECK FIELD TECHNICIAN INTERVENTION", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> viewClients(){
		Request request = new Request("VIEW CLIENTS", null);
		return sendRequest(request);
	}
	
	public ArrayList<String> viewClient(String client_Id) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_Id);
		Request request = new Request("VIEW CLIENT", arguments);
		return sendRequest(request);
	}

	public ArrayList<String> newClient(String name, String lastName,String phone_number) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(name);
		arguments.add(lastName);
		arguments.add(phone_number);
		Request request = new Request("NEW CLIENT", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> updateClient(String client_Id, String name, String lastName, String phone_number){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_Id);
		arguments.add(name);
		arguments.add(lastName);
		arguments.add(phone_number);
		Request request = new Request("UPDATE CLIENT", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> deleteClient(String client_Id){
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_Id);
		Request request = new Request("DELETE CLIENT", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> viewSubscription(String subscription_Id) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(subscription_Id);
		Request request = new Request("VIEW SUBSCRIPTION", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> newSubscription(String client_Id, String start_date, String end_date) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(client_Id);
		arguments.add(start_date);
		arguments.add(end_date);
		Request request = new Request("NEW SUBSCRIPTION", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> updateSubscription(String subscription_Id, String client_Id, String start_date, String end_date) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(subscription_Id);
		arguments.add(client_Id);
		arguments.add(start_date);
		arguments.add(end_date);
		Request request = new Request("UPDATE SUBSCRIPTION", arguments);
		return sendRequest(request);
	}
	
	public ArrayList<String> deleteSubscription(String subscription_Id) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(subscription_Id);
		Request request = new Request("DELETE SUBSCRIPTION", arguments);
		return sendRequest(request);
	}
	
	public Socket getSocket() {
		return socket;
	}

	public ArrayList<String> changeState(String userId, String state) {
		ArrayList<String> arguments = new ArrayList<>();
		arguments.add(userId);
		arguments.add(state);
		Request request = new Request("CHANGE STATE FIELD TECHNICIAN", arguments);
		return sendRequest(request);	
	}
}
