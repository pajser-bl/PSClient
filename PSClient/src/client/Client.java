package client;

import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utility.ClientResources;

public class Client extends Application {
	
	private ClientCommunication clientCommunication;
	private Stage mainStage;
	private User user;
		
	public void start(Stage primaryStage) {
		try {
			mainStage = primaryStage;
			Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenResolution.getWidth();
			double height = screenResolution.getHeight();
			ClientResources resources = new ClientResources(mainStage, clientCommunication, user, width, height);
			Parent root = FXMLLoader.load(getClass().getResource("/view/user/LoginForm.fxml"), resources);
			mainStage.setScene(new Scene(root));
			mainStage.setTitle("Road Runner");
			mainStage.setResizable(false);
			mainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
