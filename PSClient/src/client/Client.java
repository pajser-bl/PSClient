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
			ClientCommunication clientCommunication=new ClientCommunication(props.getProperty("server_ip"),Integer.parseInt(props.getProperty("server_port")));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//call gui
		
		
		
	}
	
}
