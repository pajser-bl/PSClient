package controller.supervisor;

import client.ClientCommunication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class NewClientController {

	private ClientCommunication clientComm;
	private double stageHeight;
	private double stageWidth;
	@FXML Button createButton;
	@FXML TextField name;
	@FXML TextField lastName;
	@FXML TextField phoneNumber;
	@FXML VBox labelBox;
	@FXML VBox inputBox;
	
	@FXML public void initialize() {
		resize();
	}
	
	public NewClientController(ClientCommunication clientComm, double stageHeight, double stageWidth) {
		this.clientComm = clientComm;
		this.stageHeight = stageHeight;
		this.stageWidth = stageWidth;
	}
	
	public void createNewClient(ActionEvent event) {
		
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(labelBox, stageHeight * 0.1);
		AnchorPane.setRightAnchor(labelBox, stageWidth * 0.5);
		AnchorPane.setBottomAnchor(inputBox, stageHeight * 0.1);
		AnchorPane.setLeftAnchor(inputBox, stageWidth * 0.5);
		createButton.setPrefSize(stageWidth * 0.3, stageHeight * 0.05);
	}
}
