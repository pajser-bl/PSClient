package controller.administrator;

import client.ClientCommunication;
import exception.MessageException;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import utility.MessageBox;

public class AddNewUserController {

	private ObservableList<User> users;
	private double stageHeight;
	private double stageWidth;
	private boolean userUpdate;
	private ClientCommunication clientComm;
	private Stage userStage;
	@FXML AnchorPane userDataAnchor;
	@FXML AnchorPane accountSettingsAnchor;
	@FXML Button createNewUser;
	@FXML ComboBox<String> userType;
	@FXML ComboBox<String> qualification;
	@FXML DatePicker date;
	@FXML TextField name;
	@FXML TextField lastName;
	@FXML TextField password;
	@FXML TextField licence;
	@FXML TextField username;
	@FXML VBox userDataLabelsBox;
	@FXML VBox userDataInputBox;
	@FXML VBox accountSettingsLabelsBox;
	@FXML VBox accountSettingsInputBox;

	@FXML public void initialize() {
		if(userUpdate == true) {
			setUserInfo(users.get(0));
			createNewUser.setText("Azuriraj korisnika");
			createNewUser.setOnAction(e -> updateUser(users.get(0)));
		}
		resize();
	}
	
	public AddNewUserController(Stage userStage, ClientCommunication clientComm, ObservableList<User> users , boolean userUpdate, 
			double stageWidth, double stageHeight) {
		this.userStage = userStage;
		this.clientComm = clientComm;
		this.users = users;
		this.userUpdate = userUpdate;
		this.stageWidth = stageWidth;
		this.stageHeight = stageHeight;
	}
	
	public void createNewUser(ActionEvent event) {
		try {
			if(name.getText().isEmpty())
				throw new MessageException("Unesite ime korisnika");
			if(lastName.getText().isEmpty())
				throw new MessageException("Unesite prezime korisnika");
			if(date.getValue() == null)
				throw new MessageException("Unesite datum rodjenja");
			if(username.getText().isEmpty())
				throw new MessageException("Unesite korisnicko ime");
			if(password.getText().isEmpty())
				throw new MessageException("Unesite lozinku korisnika");
			ArrayList<String> reply = clientComm.newUser(name.getText(), lastName.getText(), date.getValue().toString(),
				(String) userType.getValue(), qualification.getValue(), licence.getText(),username.getText(), password.getText());
			if (reply.get(0).equals("ADD USER OK")) {
				MessageBox.displayMessage("Potvrda", "Korisnik uspjesno kreiran");
				if(!users.isEmpty())
					users.add(new User(reply.get(1), name.getText(), lastName.getText(), userType.getValue(),
							username.getText(),null, null, null));
				userStage.close();
			} else
				MessageBox.displayMessage("Greska", reply.get(1));
		} catch (MessageException e) {
			MessageBox.displayMessage("Greska", e.toString());
		} catch (Exception e) {
			MessageBox.displayMessage("Greska", e.toString());
			e.printStackTrace();
		}
	}
	
	public void updateUser(User user) {
		
	}
	
	public void setUserInfo(User user) {
		name.setText(user.getName());
		lastName.setText(user.getLastName());
		username.setText(user.getUsername());
		date.getEditor().setText(user.getDate().toString().replace("-", "/"));
		String[] split = user.getQualification().split(", ");
		qualification.setValue(split[0]);
		if(split.length > 1)
			licence.setText(split[1]);
		userType.setValue(user.getType());
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(userDataAnchor, stageHeight * 0.6);
		AnchorPane.setTopAnchor(userDataLabelsBox, stageHeight * 0.1);
		AnchorPane.setRightAnchor(userDataLabelsBox, stageWidth * 0.5);
		AnchorPane.setTopAnchor(userDataInputBox, stageHeight * 0.1);
		AnchorPane.setLeftAnchor(userDataInputBox, stageWidth * 0.5);
		
		AnchorPane.setTopAnchor(accountSettingsAnchor, stageHeight * 0.45);
		AnchorPane.setTopAnchor(accountSettingsLabelsBox, stageHeight * 0.0001);
		AnchorPane.setBottomAnchor(accountSettingsLabelsBox, stageHeight * 0.1);
		AnchorPane.setRightAnchor(accountSettingsLabelsBox, stageWidth * 0.5);
		AnchorPane.setTopAnchor(accountSettingsInputBox, stageHeight * 0.0001);
		AnchorPane.setBottomAnchor(accountSettingsInputBox, stageHeight * 0.1);
		AnchorPane.setLeftAnchor(accountSettingsInputBox, stageWidth * 0.5);
	}
}
