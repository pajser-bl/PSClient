package controller.supervisor;

import java.time.LocalDateTime;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Session;

public class SessionsTableController {

	private double screenHeight;
	private double screenWidth;
	private ObservableList<Session> sessionsList;
	@FXML TableView<Session> sessionsTable;
	@FXML Button showSessionButton;
	
	@FXML public void initialize() {
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
	
	public SessionsTableController(ObservableList<Session> sessionsList, double screenWidth, double screenHeight) {
		this.sessionsList = sessionsList;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	
	public void showSession(ActionEvent event) {}
	
	public void resize() {
		AnchorPane.setBottomAnchor(sessionsTable, screenHeight * 0.2);
		showSessionButton.setPrefSize(screenWidth * 0.75, screenHeight * 0.1);
	}
}
