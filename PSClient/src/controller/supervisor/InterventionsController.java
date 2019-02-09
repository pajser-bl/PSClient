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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Intervention;
import model.User;
import utility.MessageBox;
import utility.TimeUtility;

public class InterventionsController {

	private ClientCommunication clientComm;
	private double screenHeight;
	private double screenWidth;
	private ObservableList<Intervention> interventionsList;
	private User user;
	@FXML AnchorPane tableAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML TableView<Intervention> interventionsTable;
	@FXML Button closeButton;
	@FXML Button viewButton;
	
	@FXML public void initialize() {
		resize();
		generateTable();
	}
	
	public InterventionsController(ObservableList<Intervention> interventionsList, ClientCommunication clientComm, User user,
			double screenWidth, double screenHeight) {
		this.clientComm = clientComm;
		this.user = user;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.interventionsList = interventionsList;
	}

	public void closeIntervention(ActionEvent event) {
		try {
			if(interventionsTable.getSelectionModel().isEmpty())
				throw new MessageException("Odaberite intervenciju");
			Stage reportStage = new Stage();
			reportStage.getIcons().add(new Image("/resources/images/logo.png"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/supervisor/ReportForm.fxml"));
			loader.setControllerFactory(e -> new ReportController(clientComm,
					interventionsTable.getSelectionModel().getSelectedItem().getId(), user.getUserId()));
			Parent root = loader.load();
			reportStage.initModality(Modality.APPLICATION_MODAL);
			reportStage.setResizable(false);
			reportStage.setScene(new Scene(root, screenWidth * 0.2, screenHeight * 0.3));
			reportStage.show();
		} catch (MessageException e) {
			MessageBox.displayMessage("Greska", e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void viewIntervention(ActionEvent event) {
		try {
			if(interventionsTable.getSelectionModel().isEmpty())
				throw new MessageException("Odaberite intervenciju");
			ArrayList<String> reply = clientComm.viewIntervention(interventionsTable.getSelectionModel().getSelectedItem().getId());
			if(reply.get(0).equals("VIEW INTERVENTION OK")) {
				Intervention intervention = new Intervention(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5),
						reply.get(6), reply.get(7), reply.get(8), reply.get(9),
						TimeUtility.stringToLocalDateTime(reply.get(10)), reply.get(11), reply.get(12), 
						TimeUtility.stringToLocalDateTime(reply.get(13)), reply.get(14), reply.get(15),
						TimeUtility.stringToLocalDateTime(reply.get(16)), reply.get(17), "", "", LocalDateTime.now());
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/InterventionForm.fxml"));
				loader.setControllerFactory(e -> new InterventionController(intervention, screenWidth * 0.25, screenHeight * 0.6));
				Parent root = loader.load();
				Stage interventionStage = new Stage();
				interventionStage.setResizable(false);
				interventionStage.getIcons().add(new Image("/resources/images/logo.png"));
				interventionStage.initModality(Modality.APPLICATION_MODAL);
				interventionStage.setScene(new Scene(root, screenWidth * 0.25, screenHeight * 0.6));
				interventionStage.show();
				} else
					MessageBox.displayMessage("Greska", reply.get(1));
			} catch (MessageException e) {
				MessageBox.displayMessage("Greska", e.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void generateTable() {
		TableColumn<Intervention, String> idColumn = new TableColumn<Intervention, String>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		TableColumn<Intervention, String> userOpenedColumn = new TableColumn<Intervention, String>("Operater otvorio");
		userOpenedColumn.setCellValueFactory(new PropertyValueFactory<>("userOpened"));
		TableColumn<Intervention, String> fieldTechnicianColumn = new TableColumn<Intervention, String>("Terenski radnik");
		fieldTechnicianColumn.setCellValueFactory(new PropertyValueFactory<>("fieldTechnician"));
		TableColumn<Intervention, String> userClosedColumn = new TableColumn<Intervention, String>("Operater zatvorio");
		userClosedColumn.setCellValueFactory(new PropertyValueFactory<>("userClosed"));
		TableColumn<Intervention, LocalDateTime> closedOnColumn = new TableColumn<Intervention, LocalDateTime>("Vrijeme zatvaranja");
		closedOnColumn.setCellValueFactory(new PropertyValueFactory<>("closedOn"));
		interventionsTable.getColumns().addAll(idColumn, userOpenedColumn, fieldTechnicianColumn, userClosedColumn, closedOnColumn);
		interventionsTable.setItems(interventionsList);
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(tableAnchor, screenHeight * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, screenHeight * 0.7);
		closeButton.setPrefSize(screenHeight * 0.175, screenHeight * 0.75);
		viewButton.setPrefSize(screenHeight * 0.175, screenHeight * 0.75);
	}
}
