package controller.supervisor;

import java.time.LocalDateTime;
import java.util.ArrayList;

import client.ClientCommunication;
import controller.user.InterventionController;
import exception.MessageException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Session;
import utility.MessageBox;

public class SessionsTableController {

	private ClientCommunication clientComm;
	private double screenHeight;
	private double screenWidth;
	private ObservableList<Session> sessionsList;
	@FXML AnchorPane tableAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML TableView<Session> sessionsTable;
	@FXML Button viewButton;
	
	@FXML public void initialize() {
		resize();
		generateTable();
	}
	
	public SessionsTableController(ObservableList<Session> sessionsList, ClientCommunication clientComm, double screenWidth,
			double screenHeight) {
		this.clientComm = clientComm;
		this.sessionsList = sessionsList;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	
	public void viewSession(ActionEvent event) {
		try {
			if(sessionsTable.getSelectionModel().isEmpty())
				throw new MessageException("Odaberite sesiju");
			ArrayList<String> reply = clientComm.viewSession(sessionsTable.getSelectionModel().getSelectedItem().getSessionId());
			System.out.print(reply.toString());
			if(reply.get(0).equals("VIEW USER SESSION OK")) {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supervisor/SessionForm.fxml"));
				loader.setControllerFactory(e -> new SessionController(reply.get(1), reply.get(2), reply.get(3), reply.get(4),
						reply.get(5), screenWidth * 0.5, screenHeight * 0.7));
				Parent root = loader.load();
				Stage sessionStage = new Stage();
				sessionStage.setResizable(false);
				sessionStage.getIcons().add(new Image("/resources/images/logo.png"));
				sessionStage.initModality(Modality.APPLICATION_MODAL);
				sessionStage.setScene(new Scene(root, screenWidth * 0.5, screenHeight * 0.7));
				sessionStage.show();
				} else
					MessageBox.displayMessage("Greska", reply.get(1));
		} catch (MessageException e) {
			MessageBox.displayMessage("Greska", e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generateTable() {
		TableColumn<Session, String> idColumn = new TableColumn<Session, String>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
		TableColumn<Session, String> userIdColumn = new TableColumn<Session, String>("Id korisnika");
		userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
		TableColumn<Session, String> userColumn = new TableColumn<Session, String>("Korisnik");
		userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
		TableColumn<Session, String> startColumn = new TableColumn<Session, String>("Pocetak sesije");
		startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
		TableColumn<Session, String> endColumn = new TableColumn<Session, String>("Kraj sesije");
		endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
		sessionsTable.getColumns().addAll(idColumn, userIdColumn, userColumn, startColumn, endColumn);
		sessionsTable.setItems(sessionsList);
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(tableAnchor, screenHeight * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, screenHeight * 0.7);
		viewButton.setPrefSize(screenHeight * 0.175, screenHeight * 0.75);
	}
}
