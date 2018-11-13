package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import utility.PasswordChangeBox;

public class UserTableController {

	@FXML Button changePasswordButton;
	
	public void passwordChange(ActionEvent event) {
		PasswordChangeBox.passwordChange();
	}
}
