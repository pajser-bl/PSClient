package controller.supervisor;

import java.util.ArrayList;

import client.ClientCommunication;
import exception.MessageException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Client;
import utility.MessageBox;

public class NewClientController {

	private ClientCommunication clientComm;
	private double stageHeight;
	private double stageWidth;
	private ObservableList<Client> clientList;
	private Stage stage;
	@FXML Button createButton;
	@FXML TextField name;
	@FXML TextField lastName;
	@FXML TextField phoneNumber;
	@FXML VBox labelBox;
	@FXML VBox inputBox;
	
	@FXML public void initialize() {
		resize();
	}
	
	public NewClientController(Stage stage, ClientCommunication clientComm, ObservableList<Client> clientList, double stageHeight,
			double stageWidth) {
		this.stage = stage;
		this.clientComm = clientComm;
		this.clientList = clientList;
		this.stageHeight = stageHeight;
		this.stageWidth = stageWidth;
	}
	
	public void createNewClient(ActionEvent event) {
		try {
			if(name.getText().isEmpty())
				throw new MessageException("Ime more biti popunjeno");
			if(lastName.getText().isEmpty())
				throw new MessageException("Prezime mora biti popunjeno");
			if(phoneNumber.getText().isEmpty())
				throw new MessageException("Broje telefona mora biti popunjen");
			ArrayList<String> reply = clientComm.newClient(name.getText(), lastName.getText(), phoneNumber.getText());
			if(reply.get(0).equals("NEW CLIENT OK")) {
				clientList.add(new Client(reply.get(1), name.getText(), lastName.getText(), phoneNumber.getText(), "ne"));
				stage.close();
				MessageBox.displayMessage("Potvrda", "Klijent uspjesno napravljen");
			}
			else 
				MessageBox.displayMessage("Greska", reply.get(1));
		} catch (MessageException e) {
			MessageBox.displayMessage("Greska", e.toString());
		}
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(labelBox, stageHeight * 0.1);
		AnchorPane.setRightAnchor(labelBox, stageWidth * 0.5);
		AnchorPane.setBottomAnchor(inputBox, stageHeight * 0.1);
		AnchorPane.setLeftAnchor(inputBox, stageWidth * 0.5);
		createButton.setPrefSize(stageWidth * 0.3, stageHeight * 0.05);
	}
}
