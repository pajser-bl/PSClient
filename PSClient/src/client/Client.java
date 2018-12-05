package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.ChoiceBox;
import utility.MessageBox;

public class Client extends Application {
	
	public static ClientCommunication clientCommunication;
	public static User user;
	public static boolean login = false;
		
	public void start(Stage primaryStage) {
		try {
		Parent root = FXMLLoader.load(getClass().getResource("/view/NotificationForm.fxml"));
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("Road Runner");
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e -> {
				e.consume();
				logout(primaryStage);
			});
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void logout(Stage primaryStage) {
		if(clientCommunication != null) {
			if(clientCommunication.getSocket() != null) {
				if(clientCommunication.getSocket().isConnected() && Client.login == true) {
					if(ChoiceBox.displayChoice("Odjava", "Da li ste sigurni da zelite da se odjavite?") == true)
						try {
							RequestFunctionality.logout(Client.clientCommunication, Client.user.getUserId());
							Client.clientCommunication.getSocket().close();
							primaryStage.close();
						} catch(Exception e) {
							MessageBox.displayMessage("Greska", "Greska kod logouta");
							primaryStage.close();
						}
				} else primaryStage.close();
			} else primaryStage.close();
		} else primaryStage.close();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
