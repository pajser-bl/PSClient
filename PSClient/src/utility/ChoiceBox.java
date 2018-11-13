package utility;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChoiceBox {
	
	private static boolean answer = false;

	public static boolean displayChoice(String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Greska");
		window.setResizable(false);
		window.setMaximized(false);
			
		Label label = new Label(message);
		Button yesButton = new Button("Da");
		yesButton.getStylesheets().add("css/profile_button.css");
		yesButton.getStyleClass().add("profilbutton");
		yesButton.setOnAction(e -> {
			answer = true;
			window.close();
		});
		Button noButton = new Button("Ne");
		noButton.getStylesheets().add("css/profile_button.css");
		noButton.getStyleClass().add("profilbutton");
		noButton.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		HBox options = new HBox();
		options.setSpacing(20);
		options.setAlignment(Pos.CENTER);
		options.getChildren().addAll(yesButton, noButton);
		
		VBox mainLayout = new VBox();
		mainLayout.getStylesheets().add("css/background.css");
		mainLayout.getStyleClass().add("root7");
		mainLayout.getChildren().addAll(label, options);
		mainLayout.setAlignment(Pos.CENTER);
		mainLayout.setSpacing(20);
		
		Scene scene = new Scene(mainLayout, 450, 150);
		window.setScene(scene);
		window.showAndWait();
		return answer;
	}
}