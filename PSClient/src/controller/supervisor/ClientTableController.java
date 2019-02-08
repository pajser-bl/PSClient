package controller.supervisor;

import model.Client;
import model.User;
import utility.MessageBox;

import java.util.ArrayList;

import client.ClientCommunication;
import exception.MessageException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
		generateTable();
	}
	
	public ClientTableController(ClientCommunication clientComm, ObservableList<Client> observableList, double screenWidth, 
			double screenHeight) {
		this.clientComm = clientComm;
		this.clientList = observableList;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	public void createClient(ActionEvent event) {
		
	}
	
	public void updateClient(ActionEvent event) {}
	
	public void deleteClient(ActionEvent event) {
		try {
			if(clientTable.getSelectionModel().isEmpty())
				throw new MessageException("Izaberite klijenta");
			Client client = clientTable.getSelectionModel().getSelectedItem();
			ArrayList<String> reply = clientComm.deleteClient(client.getId());
			if(reply.get(0).equals("DELETE CLIENT OK")) {
				clientList.remove(client);
				MessageBox.displayMessage("Potvrda", "Klijent uspjesno obrisan");
			}
			else
				MessageBox.displayMessage("Greska", reply.get(1));
		} catch (MessageException e) {
			MessageBox.displayMessage("Greska", e.toString());
		}
	}
	
	public void generateTable() {
		TableColumn<Client, String> idColumn = new TableColumn<Client, String>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<Client, String> nameColumn = new TableColumn<Client, String>("Ime");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<Client, String> lastNameColumn = new TableColumn<Client, String>("Prezime");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		TableColumn<Client, String> phoneNumberColumn = new TableColumn<Client, String>("Broj telefona");
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		clientTable.getColumns().addAll(idColumn, nameColumn, lastNameColumn, phoneNumberColumn);
		clientTable.setItems(clientList);
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(tableAnchor, screenHeight * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, screenHeight * 0.7);
		createClientButton.setPrefSize(screenHeight * 0.175, screenHeight * 0.75);
		updateClientButton.setPrefSize(screenHeight * 0.175, screenHeight * 0.75);
		deleteClientButton.setPrefSize(screenHeight * 0.175, screenHeight * 0.75);
	}
}
