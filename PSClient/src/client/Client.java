package client;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Client {
	
	
	
	
	public static void main(String ars[]) {
		//setup...
		try {
			Properties props=new Properties();
			FileInputStream fIS=new FileInputStream("client.conf");
			props.load(fIS);
			ClientCommunication clientCommunication=new ClientCommunication(props.getProperty("client.server_ip"),Integer.parseInt(props.getProperty("client.server_port")));
			RequestFunctionality requestFunctionality=new RequestFunctionality(clientCommunication);
		
			
			
			for(String s:requestFunctionality.login("a","a"))
				System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//call gui
		
		
		//test
		
		
	}
	
}
