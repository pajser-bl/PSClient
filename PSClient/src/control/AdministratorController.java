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
	@FXML AnchorPane anchor;
	
	public void addNewUser(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/AddNewUserForm.fxml"));
			if(anchor.getChildren().size() != 0)
				anchor.getChildren().remove(0);
			anchor.getChildren().add(root);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showUsers(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/UserTableForm.fxml"));
			if(anchor.getChildren().size() != 0)
				anchor.getChildren().remove(0);
			anchor.getChildren().add(root);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
