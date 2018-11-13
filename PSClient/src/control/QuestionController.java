package control;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class QuestionController {

	@FXML Button yesButton;
	@FXML Button noButton;
	@FXML Label message;
	private boolean answer;
	
	public boolean displayChoice(String choiceMessage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/ErrorForm.fxml"));
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setScene(new Scene(root));
			window.setTitle("Road Runner");
			message.setText(choiceMessage);
			Button yesButton = new Button("Da");
			yesButton.setOnAction(e -> {
				answer = true;
				window.close();
			});
			Button noButton = new Button("Ne");
			noButton.setOnAction(e -> {
				answer = false;
				window.close();
			});
				window.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return answer;
	}
}
