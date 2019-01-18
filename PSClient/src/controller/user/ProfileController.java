package controller.user;

import java.util.ArrayList;

import client.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.AdministratorResources;

public class ProfileController {

	@FXML AdministratorResources resources;
	@FXML Button updateUserButton;
	@FXML HBox avatarBox;
	@FXML HBox infoBox;
	@FXML ImageView avatar;
	@FXML Label name;
	@FXML Label lastName;
	@FXML Label username;
	@FXML Label userType;
	@FXML Label qualification;
	@FXML VBox userDataBox;

	@FXML public void initialize() {
		if(resources.getUser().getType() == "Supervizor")
			userDataBox.getChildren().remove(updateUserButton);
		resize();
		name.setText("Ime:                    " + resources.getUsers().get(0).getName());
		lastName.setText("Prezime:              " + resources.getUsers().get(0).getLastName());
		username.setText("Korisnicko ime:     " + resources.getUsers().get(0).getUsername());
		userType.setText("Tip korisnika:       " + resources.getUsers().get(0).getType());
		qualification.setText("Kvalifikacjia:        " + resources.getUsers().get(0).getQualification());
	}
	
	public void updateUser() {
		ArrayList<User> users = new ArrayList<>();
		users.add(resources.getUsers().get(0));
		AdministratorResources newResources = new AdministratorResources(null, resources.getClientCommunication(),
				resources.getUser(), resources.getScreenWidth() * 0.5, resources.getScreenHeight() * 0.7, users);
		newResources.setUserUpdate(true);
		Stage updateUserStage = new Stage();
		newResources.setStage(updateUserStage);
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/administrator/AddNewUserForm.fxml"), newResources);
			updateUserStage.setScene(new Scene(root, resources.getScreenWidth() * 0.5, resources.getScreenHeight() * 0.7));
			updateUserStage.initModality(Modality.APPLICATION_MODAL);
			updateUserStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(avatarBox, resources.getScreenHeight() * 0.8);
		AnchorPane.setTopAnchor(infoBox, resources.getScreenHeight() * 0.2);
		AnchorPane.setBottomAnchor(infoBox, resources.getScreenHeight() * 0.6);
		AnchorPane.setTopAnchor(userDataBox, resources.getScreenHeight() * 0.4);
	}
}
