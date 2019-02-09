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
import utility.MessageBox;
import utility.PdfManager;
import utility.TimeUtility;

public class ReportsController {

	private ClientCommunication clientComm;
	private double screenHeight;
	private double screenWidth;
	private ObservableList<Intervention> reportsList;
	@FXML AnchorPane tableAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML Button viewButton;
	@FXML Button exportButton;
	@FXML TableView<Intervention> reportsTable;
	
	@FXML public void initialize() {
		resize();
		generateTable();
	}
	
	public ReportsController(ObservableList<Intervention> reportsList, ClientCommunication clientComm, double screenWidth,
			double screenHeight) {
		this.clientComm = clientComm;
		this.reportsList = reportsList;
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
	}
	
	public void exportReport(ActionEvent event) {
		try {
			if(reportsTable.getSelectionModel().isEmpty())
				throw new MessageException("Odaberite izvjestaj");
			Intervention intervention = null;
			ArrayList<String> reply = clientComm.viewIntervention(reportsTable.getSelectionModel().getSelectedItem().getId());
			if(reply.get(0).equals("VIEW INTERVENTION OK")) {
				intervention = new Intervention(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5),
						reply.get(6), reply.get(7), reply.get(8), reply.get(9),
						TimeUtility.stringToLocalDateTime(reply.get(10)), reply.get(11), reply.get(12), 
						TimeUtility.stringToLocalDateTime(reply.get(13)), reply.get(14), reply.get(15),
						TimeUtility.stringToLocalDateTime(reply.get(16)), reply.get(17), reply.get(18), reply.get(19),
						TimeUtility.stringToLocalDateTime(reply.get(20)));
				if(PdfManager.exportPDF(intervention)) {
					MessageBox.displayMessage("Potvrda", "Intervencija je uspjesno eksportovana");
				} else
					MessageBox.displayMessage("Greska", "Eksportovanje nije uspjelo");
			} else
				MessageBox.displayMessage("Greska", reply.get(1));
		} catch (MessageException e) {
			MessageBox.displayMessage("Greska", e.toString());
		}
	}
	
	public void viewReport(ActionEvent event) {
		try {
			if(reportsTable.getSelectionModel().isEmpty())
				throw new MessageException("Odaberite izvjestaj");
			ArrayList<String> reply = clientComm.viewIntervention(reportsTable.getSelectionModel().getSelectedItem().getId());
			if(reply.get(0).equals("VIEW INTERVENTION OK")) {
				Intervention intervention = new Intervention(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5),
						reply.get(6), reply.get(7), reply.get(8), reply.get(9),
						TimeUtility.stringToLocalDateTime(reply.get(10)), reply.get(11), reply.get(12), 
						TimeUtility.stringToLocalDateTime(reply.get(13)), reply.get(14), reply.get(15),
						TimeUtility.stringToLocalDateTime(reply.get(16)), reply.get(17), reply.get(18), reply.get(19),
						TimeUtility.stringToLocalDateTime(reply.get(20)));
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/InterventionForm.fxml"));
				loader.setControllerFactory(e -> new InterventionController(intervention, null,
						screenWidth * 0.28, screenHeight * 0.7));
				Parent root = loader.load();
				Stage interventionStage = new Stage();
				interventionStage.setResizable(false);
				interventionStage.getIcons().add(new Image("/resources/images/logo.png"));
				interventionStage.initModality(Modality.APPLICATION_MODAL);
				interventionStage.setScene(new Scene(root, screenWidth * 0.3, screenHeight * 0.7));
				interventionStage.show();
				} else
					MessageBox.displayMessage("Greska", reply.get(1));
			} catch (MessageException e) {
				MessageBox.displayMessage("Greska", e.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void viewReport() {
		
	}
	
	public void generateTable() {
		TableColumn<Intervention, String> idColumn = new TableColumn<Intervention, String>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		TableColumn<Intervention, String> clientColumn = new TableColumn<Intervention, String>("Klijent");
		clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
		TableColumn<Intervention, String> supervisorColumn = new TableColumn<Intervention, String>("Supervisor");
		supervisorColumn.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
		TableColumn<Intervention, LocalDateTime> closedColumn = new TableColumn<Intervention, LocalDateTime>("Izvjestaj napravljen");
		closedColumn.setCellValueFactory(new PropertyValueFactory<>("closedOn"));
		reportsTable.getColumns().addAll(idColumn, clientColumn, supervisorColumn, closedColumn);
		reportsTable.setItems(reportsList);
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(tableAnchor, screenHeight * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, screenHeight * 0.7);
		exportButton.setPrefSize(screenHeight * 0.175, screenHeight * 0.75);
		viewButton.setPrefSize(screenHeight * 0.175, screenHeight * 0.75);
	}
}
