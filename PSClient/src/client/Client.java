package client;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Client {
	
	public static void main(String ars[]) {
		try {
			Properties props = new Properties();
			FileInputStream fIS = new FileInputStream("client.conf");
			props.load(fIS);
			ClientCommunication clientCommunication = new ClientCommunication(props.getProperty("client.server_ip"),
																			  Integer.parseInt(props.getProperty("client.server_port")));
			RequestFunctionality requestFunctionality = new RequestFunctionality(clientCommunication);
		
			for(String s:requestFunctionality.login("a", "a"))
				System.out.println(s);
			/*for(String s:requestFunctionality.newCredentials("3", "b", "b"))
				//System.out.println(s);
			for(String s:requestFunctionality.updateCredentials("2", "c", "c"))
				System.out.println(s);
			for(String s:requestFunctionality.deleteCredentials("2"))
				System.out.println(s);
			for(String s:requestFunctionality.newUser("Zeljka", "Cvijanovic", "1967-08-02", "Administrator", "Prevelika"))
				System.out.println(s);*/
			//for(String s:requestFunctionality.updateUser("1", "Darken", "Andric", "1987-01-01", "Supervizor", "Kaskader"))
				//System.out.println(s);
			//for(String s:requestFunctionality.newCredentials("2", "b", "b"))
				//System.out.println(s);
//			for(String s:requestFunctionality.deleteUser("2"))
//				System.out.println(s);

//			for(String s:requestFunctionality.updateClient("2", "D", "D", "3456"))
//			System.out.println(s);
			
			for(String s:requestFunctionality.deleteClient("1"))
				System.out.println(s);
			
//			for(String s:requestFunctionality.viewClient("3"))
//			System.out.println(s);
			
			
			for(String s:requestFunctionality.logout())
				System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
