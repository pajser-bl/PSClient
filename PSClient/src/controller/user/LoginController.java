package controller.user;

import client.ClientCommunication;
import controller.administrator.AdministratorController;
import controller.fieldTechnician.FieldTechnicianController;
import controller.operater.OperaterController;
import controller.supervisor.SupervisorController;
import exception.ConnectionTimeoutException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.FieldTechnician;
import model.User;
import javafx.scene.control.Button;
import utility.MessageBox;

public class LoginController{

		private Stage mainStage;
		private ClientCommunication clientComm;
		private double screenHeight;
		private double screenWidth;
		private User user;
		@FXML TextField username;
		@FXML PasswordField password;
		@FXML Button loginButton;
		
		public LoginController(Stage mainStage, double screenWidth, double screenHeight) {
			this.mainStage = mainStage;
			this.screenWidth = screenWidth;
			this.screenHeight = screenHeight;
		}
		
		public void login(ActionEvent loginEvent) {
			try {
				clientComm = (new ClientCommunication("192.168.1.3", 9000));
				ArrayList<String> reply = clientComm.login(username.getText(), password.getText());
				if(reply.get(0).equals("LOGIN OK")) {
					user  = new User(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5),null, null, null);
					if(reply.get(4).equals("Operater")) {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/operater/OperaterForm.fxml"));
						loader.setControllerFactory(e -> new OperaterController(mainStage, clientComm, user, screenWidth,
								screenHeight));
						Parent operaterView = loader.load();
						mainStage.setScene(new Scene(operaterView));
					} else if(reply.get(4).equals("Terenski Radnik")) {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/field_technician/FieldTechnicianForm.fxml"));
						FieldTechnician technicianUser = new FieldTechnician(user.getUserId(), user.getName(), user.getLastName(),
								"neaktivan");
						loader.setControllerFactory(e -> new FieldTechnicianController(mainStage, clientComm, technicianUser, screenWidth,
								screenHeight));
						Parent technicianView = loader.load();
						mainStage.setScene(new Scene(technicianView));
					} else if(reply.get(4).equals("Supervizor")) {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supervisor/SupervisorForm.fxml"));
						loader.setControllerFactory(e -> new SupervisorController(mainStage, clientComm, user, screenWidth,
								screenHeight));
						Parent supervisorView = loader.load();
						mainStage.setScene(new Scene(supervisorView));
					} else {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/administrator/AdministratorForm.fxml"));
						loader.setControllerFactory(e -> new AdministratorController(mainStage, clientComm, user, screenWidth,
								screenHeight));
						Parent administratorView = loader.load();
						mainStage.setScene(new Scene(administratorView));
					}
					mainStage.hide();
					mainStage.setResizable(false);
					mainStage.setMaximized(true);
					mainStage.show();
				} else {
					
					clientComm.closeConnection();
					MessageBox.displayMessage("Greska", reply.get(1));
				}
			} catch (ConnectionTimeoutException e) {
				MessageBox.displayMessage("Greska", "Veza sa serverom nije uspostavljena");
			} catch (Exception e) {
				e.printStackTrace();
				clientComm.closeConnection();
			}
		}
}
