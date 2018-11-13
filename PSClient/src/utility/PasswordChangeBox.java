package utility;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PasswordChangeBox {

	public static void passwordChange() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Promjena lozinke");
		window.setResizable(false);
		window.setMaximized(false);
		
		Label lblUser = new Label("Nova lozinka:");
		lblUser.setTextFill(Color.web("#21947b"));
		
		TextField pswField = new TextField();
		pswField.getStylesheets().add("css/text_field.css");
		pswField.getStyleClass().add("text_field");
		
		HBox password = new HBox();
		password.setSpacing(20);
		password.setAlignment(Pos.CENTER);
		password.getChildren().addAll(lblUser, pswField);
	
		Button closeButton = new Button("U redu");
		closeButton.getStylesheets().add("css/profile_button.css");
		closeButton.getStyleClass().add("profilbutton");
		closeButton.setOnAction(e -> window.close());
		
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
}
