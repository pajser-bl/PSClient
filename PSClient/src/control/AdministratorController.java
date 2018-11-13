package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdministratorController {

	@FXML Button addNewUserButton;
	@FXML AnchorPane table;
	
	public void addNewUser(ActionEvent event) {
		Stage addNewUserStage = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/AddNewUserForm.fxml"));
			addNewUserStage.setScene(new Scene(root));
			addNewUserStage.setTitle("Road Runner");
			addNewUserStage.initModality(Modality.APPLICATION_MODAL);
			addNewUserStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showUsers(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/UserTableForm.fxml"));
			table.getChildren().add(root);
		} catch(Exception e) {}
	}
}
