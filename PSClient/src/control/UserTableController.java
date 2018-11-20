package control;

import client.Client;
import client.RequestFunctionality;
import client.User;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.MessageBox;
import utility.PasswordChangeBox;

public class UserTableController implements Initializable{

	@FXML TableView<User> userTable;
	@FXML Button changePasswordButton;
	@FXML Button deleteUserButton;
	
	public void initialize(URL url, ResourceBundle resourceBundle) {
		TableColumn<User, String> idColumn = new TableColumn<User, String>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
		TableColumn<User, String> nameColumn = new TableColumn<User, String>("Ime");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<User, String> lastNameColumn = new TableColumn<User, String>("Prezime");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		TableColumn<User, String> userNameColumn = new TableColumn<User, String>("Korisnicko ime");
		userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
		TableColumn<User, String> typeColumn = new TableColumn<User, String>("Tip korisnika");
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		userTable.getColumns().addAll(idColumn, nameColumn, lastNameColumn, userNameColumn, typeColumn);
		userTable.setItems(AdministratorController.getUsers());
	}
	
	public void deleteUser(ActionEvent event) {
		ArrayList<String> reply = RequestFunctionality.deleteUser(Client.clientCommunication,
																  userTable.getSelectionModel().getSelectedItem().getUserId());
		if(reply.get(0) == "DELETE USER OK") {
			AdministratorController.getUsers().remove(userTable.getSelectionModel().getSelectedItem());
			MessageBox.displayMessage("Administrator", "Korisnik uspjesno obrisan");
		} else MessageBox.displayMessage("Greska", reply.get(1));
	}
	
	public void passwordChange(ActionEvent event) {
		PasswordChangeBox.passwordChange();
	}
	
	public TableView<User> getUserTable() {
		return userTable;
	}
}
