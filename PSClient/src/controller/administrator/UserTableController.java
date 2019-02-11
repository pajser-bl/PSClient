package controller.administrator;

import client.ClientCommunication;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.User;
import utility.MessageBox;
import utility.PasswordChangeBox;

public class UserTableController {

	private ObservableList<User> users;
	private ClientCommunication clientComm;
	private double screenHeight;
	private double screenWidth;
	@FXML AnchorPane tableAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML TableView<User> userTable;
	@FXML Button changePasswordButton;
	@FXML Button deleteUserButton;
	
	public void initialize() {
		resize();
		generateTable();
	}
	
	public UserTableController(ClientCommunication clientComm, ObservableList<User> users, double screenWidth,
			double screenHeight) {
		this.clientComm = clientComm;
		this.users = users; 
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	public void deleteUser(ActionEvent event) {
		ArrayList<String> reply = clientComm.deleteUser(userTable.getSelectionModel().getSelectedItem().getUserId());
		if(reply.get(0).equals("DELETE USER OK")) {
			users.remove(userTable.getSelectionModel().getSelectedItem());
			MessageBox.displayMessage("Administrator", "Korisnik uspjesno obrisan");
		} else MessageBox.displayMessage("Greska", reply.get(1));
	}
	
	public void passwordChange(ActionEvent event) {
		PasswordChangeBox.passwordChange(userTable.getSelectionModel().getSelectedItem().getUserId(), clientComm);
	}
	
	public void generateTable() {
		TableColumn<User, String> idColumn = new TableColumn<User, String>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
		TableColumn<User, String> nameColumn = new TableColumn<User, String>("Ime");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<User, String> lastNameColumn = new TableColumn<User, String>("Prezime");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		TableColumn<User, String> usernameColumn = new TableColumn<User, String>("Korisnicko ime");
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
		TableColumn<User, String> typeColumn = new TableColumn<User, String>("Tip korisnika");
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		userTable.getColumns().addAll(idColumn, nameColumn, lastNameColumn, usernameColumn, typeColumn);
		userTable.setItems(users);
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(tableAnchor, screenHeight * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, screenHeight * 0.7);
		deleteUserButton.setPrefSize(screenHeight * 0.2, screenHeight * 0.5);
		changePasswordButton.setPrefSize(screenHeight * 0.2, screenHeight * 0.5);
	}
	
	public TableView<User> getUserTable() {
		return userTable;
	}
}
