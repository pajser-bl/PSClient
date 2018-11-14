package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
	
	public static ClientCommunication clientCommunication;
	public static User user;
		
	public void start(Stage primaryStage) {
		try {
		Parent root = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
			primaryStage.setScene(new Scene(root));
			primaryStage.setTitle("Road Runner");
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
