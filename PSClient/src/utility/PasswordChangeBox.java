package utility;

import java.util.ArrayList;

import client.ClientCommunication;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PasswordChangeBox {

	private static TextField pswField = new TextField();
	
	public static void passwordChange(String userId, ClientCommunication clientComm) {
		Stage window = new Stage();
		window.getIcons().add(new Image("/resources/images/logo.png"));
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Promjena lozinke");
		window.setResizable(false);
		window.setMaximized(false);
		
		Label lblUser = new Label("Nova lozinka:");
		lblUser.setTextFill(Color.web("#21947b"));
		
		pswField.getStylesheets().add("css/text_field.css");
		pswField.getStyleClass().add("text_field");
		
		HBox password = new HBox();
		password.setSpacing(20);
		password.setAlignment(Pos.CENTER);
		password.getChildren().addAll(lblUser, pswField);
	
		Button closeButton = new Button("U redu");
		closeButton.getStylesheets().add("css/profile_button.css");
		closeButton.getStyleClass().add("profilbutton");
		closeButton.setDefaultButton(true);
		closeButton.setOnAction(e -> sendRequest(window, userId, clientComm));
		
		VBox mainLayout = new VBox();
		mainLayout.getStylesheets().add("css/background.css");
		mainLayout.getStyleClass().add("root7");
		mainLayout.getChildren().addAll(password, closeButton);
		mainLayout.setAlignment(Pos.CENTER);
		mainLayout.setSpacing(20);
		
		Scene scene = new Scene(mainLayout, 450, 150);
		window.setScene(scene);
		window.showAndWait();
	}
	
	public static void sendRequest(Stage window, String userId, ClientCommunication clientComm) {
		if(pswField.getText().isEmpty()) {
			MessageBox.displayMessage("Greska", "Morate unijeti novu lozinku");
		} 
		else {
			ArrayList<String> reply = clientComm.changePassword(userId, pswField.getText());
			if(reply.get(0).equals("CHANGE PASSWORD FAILED"))
				MessageBox.displayMessage("Greska", reply.get(1));
			else MessageBox.displayMessage("Potvrda" , "Lozinka uspjesno promjenjena.");
		}
		window.close();
	}
}
