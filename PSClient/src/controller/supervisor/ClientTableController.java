package controller.supervisor;

import model.Client;
import client.ClientCommunication;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ClientTableController {

	private ObservableList<Client> clientList;
	private ClientCommunication clientComm;
	private double screenHeight;
	private double screenWidth;
	@FXML AnchorPane optionsAnchor;
	@FXML AnchorPane tableAnchor;
	@FXML Button createClientButton;
	@FXML Button deleteClientButton;
	@FXML Button updateClientButton;
	@FXML TableView<Client> clientTable;

	@FXML public void initialize() {
		resize();
	}
	
	public ClientTableController(ClientCommunication clientComm, ObservableList<Client> observableList, double screenWidth, double screenHeight) {
		this.clientComm = clientComm;
		this.clientList = observableList;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	public void createClient(ActionEvent event) {}
	public void updateClient(ActionEvent event) {}
	public void deleteClient(ActionEvent event) {}
	
	public void resize() {
		AnchorPane.setBottomAnchor(tableAnchor, screenHeight * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, screenHeight * 0.7);
		createClientButton.setPrefSize(screenHeight * 0.5, screenHeight * 0.1);
		updateClientButton.setPrefSize(screenHeight * 0.5, screenHeight * 0.1);
		deleteClientButton.setPrefSize(screenHeight * 0.5, screenHeight * 0.1);
	}
}
