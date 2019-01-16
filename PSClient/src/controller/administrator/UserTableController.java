package controller.administrator;

import client.User;
import controller.user.ProfileController;
import exception.EmptyFieldException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.UsersListResources;
import utility.MessageBox;
import utility.PasswordChangeBox;

public class UserTableController {

	@FXML AnchorPane tableAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML TableView<User> userTable;
	@FXML Button changePasswordButton;
	@FXML Button deleteUserButton;
	@FXML Button profileButton;
	@FXML UsersListResources resources;
	
	public void initialize() {
		resize();
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
		userTable.setItems(resources.getObservableUsers());
	}
	
	public void deleteUser(ActionEvent event) {
		ArrayList<String> reply = resources.getClientCommunication().deleteUser(userTable.getSelectionModel().getSelectedItem().getUserId());
		if(reply.get(0).equals("DELETE USER OK")) {
			resources.getUsers().remove(userTable.getSelectionModel().getSelectedItem());
			userTable.setItems(resources.getObservableUsers());
			MessageBox.displayMessage("Administrator", "Korisnik uspjesno obrisan");
		} else MessageBox.displayMessage("Greska", reply.get(1));
	}
	
	public void passwordChange(ActionEvent event) {
		PasswordChangeBox.passwordChange(userTable.getSelectionModel().getSelectedItem().getUserId(), resources);
	}
	
	public void showUser(ActionEvent event) {
		try {
			if(userTable.getSelectionModel().isEmpty())
				throw (new EmptyFieldException());
			String id = userTable.getSelectionModel().getSelectedItem().getUserId();
			ArrayList<String> reply = resources.getClientCommunication().getUser(id);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ProfilForm.fxml"));
			Parent profileView = loader.load();
			ProfileController controller = loader.getController();
			controller.showProfile(reply.get(2), reply.get(3), reply.get(7), reply.get(6), reply.get(4));
			Scene profileScene = new Scene(profileView);
			Stage profileWindow = new Stage();
			profileWindow.setScene(profileScene);
			profileWindow.initModality(Modality.APPLICATION_MODAL);
			profileWindow.show();
		} catch (EmptyFieldException e) {
			MessageBox.displayMessage("Greska", "Odaberite Korisnika");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(tableAnchor, resources.getScreenHeight() * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, resources.getScreenHeight() * 0.7);
		profileButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
		deleteUserButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
		changePasswordButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
	}
	
	public TableView<User> getUserTable() {
		return userTable;
	}
}
