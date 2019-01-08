package new_forms;

import client.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utility.ClientResources;

public class NewUserTableController {

	@FXML AnchorPane tablePane;
	@FXML AnchorPane optionsPane;
	@FXML Button profileButton;
	@FXML Button changePasswordButton;
	@FXML Button deleteUserButton;
	@FXML TableView<User> userTable;
	@FXML ClientResources resources;
	
	@FXML public void initialize() {
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
		resize();
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(tablePane, resources.getScreenHeight() * 0.1);
		AnchorPane.setTopAnchor(optionsPane, resources.getScreenHeight() * 0.7);
		profileButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
		deleteUserButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
		changePasswordButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
	}
	
	public void showUser() {}
	public void passwordChange() {}
	public void deleteUser() {}
}
