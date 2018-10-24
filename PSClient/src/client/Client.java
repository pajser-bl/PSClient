package client;

import java.io.File;
import com.sun.tools.script.shell.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
	
	private Stage primaryStage;
	private BorderPane mainLayout;
		
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
			primaryStage.setScene(new Scene(root, 250, 250));
			primaryStage.setTitle("Road Runner");
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
