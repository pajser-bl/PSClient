package controller.supervisor;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;

public class ActiveUsersTableController {

	private ObservableList<User> activeUsersList;
	@FXML TableView<User> activeUsersTable;
	
	@FXML public void initialize(){
		generateTable();
	}
	
	public ActiveUsersTableController(ObservableList<User> activeUsersList) {
		this.activeUsersList = activeUsersList;
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
		activeUsersTable.getColumns().addAll(idColumn, nameColumn, lastNameColumn, usernameColumn, typeColumn);
		activeUsersTable.setItems(activeUsersList);
	}
}
