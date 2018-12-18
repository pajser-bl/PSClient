package controller.operater;

import client.Intervention;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.ClientResources;

public class InterventionsController {
	
	@FXML AnchorPane anchor;
	@FXML TableView<Intervention> interventionsTable;
	@FXML ClientResources resources;
	
	
	@FXML public void initialize() {
		TableColumn<Intervention, String> idColumn = new TableColumn<Intervention, String>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		TableColumn<Intervention, String> stateColumn = new TableColumn<Intervention, String>("Stanje");
		stateColumn.setCellValueFactory(new PropertyValueFactory<>("closed"));
		TableColumn<Intervention, String> openedOnColumn = new TableColumn<Intervention, String>("Datum otvaranja");
		openedOnColumn.setCellValueFactory(new PropertyValueFactory<>("openedOn"));
		TableColumn<Intervention, String> openedColumn = new TableColumn<Intervention, String>("Vrijeme otvaranja");
		openedColumn.setCellValueFactory(new PropertyValueFactory<>("openedOn"));
		TableColumn<Intervention, String> closedOnColumn = new TableColumn<Intervention, String>("Vrijeme zatvaranja");
		closedOnColumn.setCellValueFactory(new PropertyValueFactory<>("closedOn"));
		interventionsTable.getColumns().addAll(idColumn, stateColumn, openedOnColumn, openedColumn, closedOnColumn);
		interventionsTable.setItems(utility.TestingClass.generateInterventions());
	}
	
	public void openNewIntervention(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/view/operater/NewInterventionForm.fxml"), resources);
			Stage interventionStage = new Stage();
			interventionStage.setScene(new Scene(root));
			interventionStage.initModality(Modality.APPLICATION_MODAL);
			interventionStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
