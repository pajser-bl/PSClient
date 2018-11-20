package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;

import utility.MessageBox;

import com.google.gson.Gson;

public class ClientCommunication {
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public ClientCommunication(String ip, int port) throws Exception {
		socket = new Socket();
		socket.connect(new InetSocketAddress(ip, port), 5000);
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	}
	
	public ArrayList<String> sendRequest(Request request) {
		output.println(new Gson().toJson(request));
		try {
			return new Gson().fromJson(input.readLine(), new TypeToken<ArrayList<String>>(){}.getType());
		}
		catch(Exception e) {
			MessageBox.displayMessage("Greska", "Veza sa serverom je prekinuta.");
			return null;
		}
	}
	
	public void logout(Request request) {
		output.println(new Gson().toJson(request));
	}
	
	public Socket getSocket() {
		return socket;
	}
}
