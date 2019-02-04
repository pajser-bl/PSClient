package client;

import controller.user.LoginController;
import java.awt.Dimension;
import java.awt.Toolkit;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
	
	private Stage mainStage;

	public static void main(String[] args) {
		launch(args);
	}
		
	public void start(Stage primaryStage) {
		try {
			this.mainStage = primaryStage;
			Dimension screenResolution = Toolkit.getDefaultToolkit().getScreenSize();
			double screenWidth = screenResolution.getWidth();
			double screenHeight = screenResolution.getHeight();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/LoginForm.fxml"));
			loader.setControllerFactory(e -> new LoginController(mainStage, screenWidth, screenHeight));
			Parent root = loader.load();
			mainStage.setScene(new Scene(root));
			mainStage.setTitle("Road Runner");
			mainStage.setResizable(false);
			mainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
