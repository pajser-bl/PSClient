package controller.supervisor;
import java.util.ArrayList;
import client.ClientCommunication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Client;
import model.Intervention;
import model.Session;
import model.User;
import utility.ChoiceBox;
import utility.MessageBox;

public class SupervisorController {

	private ClientCommunication clientComm;
	private User user;
	private Stage mainStage;
	private double screenHeight;
	private double screenWidth;
	@FXML AnchorPane avatarAnchor;
	@FXML AnchorPane menuAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML AnchorPane statusAnchor;
	@FXML AnchorPane workspaceAnchor;
	@FXML Button activeUsersButton;
	@FXML Button clientsButton;
	@FXML Button sessionsButton;
	@FXML Button interventionsButton;
	@FXML Button logoutButton;
	@FXML Button helpButton;
	@FXML Button reportsButton;
	@FXML Label name;
	@FXML Label lastName;
	@FXML ImageView avatar;
	@FXML VBox userData;
	
	@FXML public void initialize() {
		resize();
		name.setText("  " + user.getName());
		lastName.setText("  " + user.getLastName());
		mainStage.setOnCloseRequest(e -> {
			e.consume();
			close();
		});
	}
	
	public SupervisorController(Stage mainStage, ClientCommunication clientComm, User user, double screenWidth, double screenHeight) {
		this.mainStage = mainStage;
		this.clientComm = clientComm;
		this.user = user;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}
	
	public void showActiveUsers(ActionEvent event) {
		ArrayList<String> reply = clientComm.viewActiveUsers();
		if(reply.get(0).equals("VIEW ACTIVE USERS OK")) {
			if(workspaceAnchor.getChildren().size() > 0)
				workspaceAnchor.getChildren().clear();
			ArrayList<User> users = new ArrayList<>();
			for(int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
				String[] userSplit = reply.get(i + 2).split(":");
				User user = new User(userSplit[0], userSplit[1], userSplit[2], userSplit[3], userSplit[4], "", "", null);
				users.add(user);
			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supervisor/ActiveUsersTableForm.fxml"));
			loader.setControllerFactory(e -> new ActiveUsersTableController(FXCollections.observableArrayList(users)));
			try {
				Parent usersTable = loader.load();
				workspaceAnchor.getChildren().add(usersTable);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			MessageBox.displayMessage("Greska", reply.get(1));
	}
	
	public void showClients(ActionEvent event) {
		ArrayList<String> reply = clientComm.viewClients();
		ArrayList<Client> clientList = new ArrayList<>();
		if(reply.get(0).equals("VIEW CLIENTS OK")) {
			if(workspaceAnchor.getChildren().size() > 0)
				workspaceAnchor.getChildren().clear();
			for(int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
				String[] parsed = reply.get(i + 2).split(":");
				Client client = new Client(parsed[0], parsed[1], parsed[2], parsed[3], parsed[4]);
				clientList.add(client);
			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supervisor/ClientTableForm.fxml"));
			loader.setControllerFactory(e -> new ClientTableController(clientComm, FXCollections.observableArrayList(clientList),
					screenWidth, screenHeight));
			try {
				Parent clientTable = loader.load();
				if(workspaceAnchor.getChildren().size() != 0)
					workspaceAnchor.getChildren().clear();
				workspaceAnchor.getChildren().add(clientTable);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void showSessions(ActionEvent event) {
		ArrayList<String> reply = clientComm.viewSessions();
		ArrayList<Session> sessions = new ArrayList<>();
		if(reply.get(0).equals("VIEW SESSIONS OK")) {
			if(workspaceAnchor.getChildren().size() > 0)
				workspaceAnchor.getChildren().clear();
			for(int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
				String[] parse = reply.get(i + 2).split(":");
				Session session = new Session(parse[0], parse[1], parse[2] + " " + parse[3], parse[4].replace(";", ":"),
						parse[5].replace(";", ":"));
				sessions.add(session);
			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supervisor/SessionsTableForm.fxml"));
			loader.setControllerFactory(e -> new SessionsTableController(FXCollections.observableArrayList(sessions), clientComm,
					screenWidth, screenHeight));
			try {
				Parent root = loader.load();
				workspaceAnchor.getChildren().add(root);
			} catch (Exception e){
				e.printStackTrace();
			}
		} else
			MessageBox.displayMessage("Greska", reply.get(1));
	}
	
	public void viewReports(ActionEvent event) {
		ArrayList<String> reply = clientComm.viewReports();
		if(reply.get(0).equals("VIEW REPORTS OK")) {
			if(workspaceAnchor.getChildren().size() > 0)
				workspaceAnchor.getChildren().clear();
			ArrayList<Intervention> reports = new ArrayList<>();
			Intervention report = null;
			for(int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
				String[] parsed = reply.get(i + 2).split(":");
				report = new Intervention(parsed[0], parsed[1], parsed[2], parsed[3].replace(";", ":"));
				reports.add(report);
			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supervisor/ReportsTableForm.fxml"));
			loader.setControllerFactory(e -> new ReportsController(FXCollections.observableArrayList(reports), clientComm,
					screenWidth, screenHeight));
			try {
				Parent root = loader.load();
				workspaceAnchor.getChildren().add(root);
			} catch (Exception e){
				e.printStackTrace();
			}
		} else
			MessageBox.displayMessage("Greska", reply.get(1));
	}
	
	public void showInterventions(ActionEvent event) {
		ArrayList<String> reply = clientComm.closedInterventions();
		if(reply.get(0).equals("VIEW CLOSED INTERVENTIONS OK")) {
			if(workspaceAnchor.getChildren().size() > 0)
				workspaceAnchor.getChildren().clear();
			ArrayList<Intervention> interventions = new ArrayList<>();
			Intervention intervention = null;
			for(int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
				String[] parsed = reply.get(i + 2).split(":");
				intervention = new Intervention(parsed[0], parsed[1], parsed[2], parsed[3], parsed[4].replace(";", ":"));
				interventions.add(intervention);
			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supervisor/InterventionsTableForm.fxml"));
			loader.setControllerFactory(e -> new InterventionsController(FXCollections.observableArrayList(interventions), clientComm,
					user, screenWidth, screenHeight));
			try {
				Parent root = loader.load();
				workspaceAnchor.getChildren().add(root);
			} catch (Exception e){
				e.printStackTrace();
			}
		} else
			MessageBox.displayMessage("Greska", reply.get(1));
	}
	
	public void logout() {
		close();
	}
	
	public void close() {
		boolean answer = ChoiceBox.displayChoice("Odjava", "Da li ste sigurni da zelite da se odjavite?");
		if(answer) {
			clientComm.logout(user.getUserId());
			clientComm.closeConnection();
			mainStage.hide();
		}
	}
	public void resize() {
		AnchorPane.setBottomAnchor(statusAnchor, screenHeight * 0.7715);
		AnchorPane.setTopAnchor(menuAnchor, screenHeight * 0.2);
		AnchorPane.setRightAnchor(menuAnchor, screenWidth * 0.8);
		AnchorPane.setTopAnchor(workspaceAnchor, screenHeight * 0.2);
		AnchorPane.setLeftAnchor(workspaceAnchor, screenWidth * 0.2);
		AnchorPane.setRightAnchor(workspaceAnchor, screenWidth * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, screenHeight * 0.2);
		AnchorPane.setLeftAnchor(optionsAnchor, screenWidth * 0.9);
		AnchorPane.setRightAnchor(avatarAnchor, screenWidth * 0.9);
		AnchorPane.setLeftAnchor(userData, screenWidth * 0.1);
		AnchorPane.setRightAnchor(userData, screenWidth * 0.8);
		avatar.setFitHeight(screenHeight * 0.8);
		avatar.setFitWidth(screenWidth * 0.1);
		activeUsersButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		clientsButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		sessionsButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		interventionsButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		reportsButton.setPrefSize(screenWidth * 0.2, screenHeight * 0.1125);
		logoutButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
		helpButton.setPrefSize(screenWidth * 0.1, screenHeight * 0.15);
	}
}
