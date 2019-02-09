package controller.supervisor;

import client.ClientCommunication;
import exception.MessageException;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Client;
import utility.MessageBox;

public class ClientTableController {

	private ObservableList<Client> clientList;
	private ClientCommunication clientComm;
	private double screenHeight;
	private double screenWidth;
	@FXML AnchorPane optionsAnchor;
	@FXML AnchorPane tableAnchor;
	@FXML Button createClientButton;
	@FXML Button subscriptionButton;
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
		Stage clientStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supervisor/NewClientForm.fxml"));
		loader.setControllerFactory(e -> new NewClientController(clientStage, clientComm, clientList, screenWidth * 0.2,
				screenHeight * 0.3));
		try {
			Parent root = loader.load();
			clientStage.setResizable(false);
			clientStage.getIcons().add(new Image("/resources/images/logo.png"));
			clientStage.initModality(Modality.APPLICATION_MODAL);
			clientStage.setScene(new Scene(root, screenWidth * 0.2, screenHeight * 0.3));
			clientStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void subscribeClient(ActionEvent event) {
		try {
			if(clientTable.getSelectionModel().isEmpty())
				throw new MessageException("Odaberite klijenta");
			if(clientTable.getSelectionModel().getSelectedItem().getSubscription().equals("da"))
				throw new MessageException("Korisnik je vec pretplacen");
			ArrayList<String> reply = clientComm.subscribeClient(clientTable.getSelectionModel().getSelectedItem().getId());
			if(reply.get(0).equals("NEW SUBSCRIPTION OK")) {
				clientTable.getSelectionModel().getSelectedItem().setSubscription("da");
				MessageBox.displayMessage("Potvrda", "Preplata uspjesno produzena");
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
		TableColumn<Client, String> subscriptionColumn = new TableColumn<Client, String>("Pretplacen");
		subscriptionColumn.setCellValueFactory(new PropertyValueFactory<>("subscription"));
		clientTable.getColumns().addAll(idColumn, nameColumn, lastNameColumn, phoneNumberColumn, subscriptionColumn);
		clientTable.setItems(clientList);
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(tableAnchor, screenHeight * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, screenHeight * 0.7);
		createClientButton.setPrefSize(screenHeight * 0.175, screenHeight * 0.75);
		subscriptionButton.setPrefSize(screenHeight * 0.175, screenHeight * 0.75);
	}
}
