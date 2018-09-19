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

	Socket socket;
	BufferedReader input;
	PrintWriter output;
	
	public ClientCommunication(String host, int port) {
		try {
			socket = new Socket(host, port);
			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		}
		catch(Exception e) {
			System.out.println("Veza nije uspostavljena !");
		}
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
	
}
