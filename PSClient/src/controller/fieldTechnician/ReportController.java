package controller.fieldTechnician;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class ReportController {

	@FXML Button reportButton;
	@FXML TextArea reportArea;
	
	public void sendReport(ActionEvent event) {
		System.out.print(reportArea.getText());
	}
}
