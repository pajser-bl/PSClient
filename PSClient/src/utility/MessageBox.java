package utility;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MessageBox {

	public static void displayMessage(String title, String message) {
		Stage window = new Stage();
		window.getIcons().add(new Image("/resources/images/logo.png"));
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setResizable(false);
		window.setMaximized(false);
		
		Label label = new Label(message);
		label.setFont(new Font(20));
		label.setTextFill(Color.web("#21947b"));
		Button closeButton = new Button("U redu");
		closeButton.setDefaultButton(true);
		closeButton.getStylesheets().add("css/profile_button.css");
		closeButton.getStyleClass().add("profilbutton");
		closeButton.setOnAction(e -> window.close());
		
		VBox mainLayout = new VBox();
		mainLayout.getStylesheets().add("css/background.css");
		mainLayout.getStyleClass().add("root7");
		mainLayout.getChildren().addAll(label, closeButton);
		mainLayout.setAlignment(Pos.CENTER);
		mainLayout.setSpacing(20);
		
		Scene scene = new Scene(mainLayout, 450, 150);
		window.setScene(scene);
		window.showAndWait();
	}
}
