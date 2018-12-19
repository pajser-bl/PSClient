package controller.operater;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import utility.ClientResources;
import utility.MessageBox;

public class NewInterventionController {

	@FXML TextField clientName;
	@FXML TextField clientLastName;
	@FXML TextField clientPhoneNumber;
	@FXML TextField licencePlate;
	@FXML TextField manufacturer;
	@FXML TextField model;
	@FXML TextField yearMade;
	@FXML ComboBox<String> fieldTechnician;
	@FXML ClientResources resources;
	
	public void openNewIntervention(ActionEvent event) {
		System.out.println(licencePlate.getText());
		System.out.println(manufacturer.getText());
		System.out.println(model.getText());
		System.out.println(yearMade.getText());
		/*if(clientName.getText().isEmpty() || clientLastName.getText().isEmpty() || clientPhoneNumber.getText().isEmpty() ||
				licencePlate.getText().isEmpty())
			MessageBox.displayMessage("Greska", "Obiljezena polja moraju biti popunjena");
		ArrayList<String> reply = new ArrayList<>();*/
	}
}
