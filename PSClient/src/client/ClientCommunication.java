package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

public class ClientCommunication {
	private Socket socket;
	private BufferedReader input;
	private PrintWriter output;
	
	public ClientCommunication(String host, int port) throws Exception {
		socket = new Socket(host, port);
		input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	}
	
	public ArrayList<String> sendRequest(Request request) {
		output.println(new Gson().toJson(request));
		try {
			return new Gson().fromJson(input.readLine(), new TypeToken<ArrayList<String>>(){}.getType());
		}
		catch(Exception e) {
			System.out.println("Greska kod odogvora");
			return null;
		}
	}
	
	public boolean isConnected() {
		return socket.isConnected();
	}
}
