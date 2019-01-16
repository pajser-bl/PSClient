package controller.operater;

import client.Intervention;
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
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.ClientResources;

public class InterventionsController {
	
	@FXML AnchorPane tableAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML Button openNewInterventionButton;
	@FXML Button viewInterventionButton;
	@FXML TableView<Intervention> interventionsTable;
	@FXML ClientResources resources;
	
	
	@FXML public void initialize() {
		resize();
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
	}
	
	public void openNewIntervention(ActionEvent event) {
		ClientResources interventionResources = new ClientResources(null, resources.getClientCommunication(), resources.getUser(),
				resources.getScreenWidth() * 0.33, resources.getScreenHeight() * 0.7);
		try {
			Stage addNewUserStage = new Stage();
			addNewUserStage.setResizable(false);
			addNewUserStage.initModality(Modality.APPLICATION_MODAL);
			Parent root = FXMLLoader.load(getClass().getResource("/view/operater/NewInterventionForm.fxml"), interventionResources);
			Scene addNewUserScene = new Scene(root, interventionResources.getScreenWidth(), interventionResources.getScreenHeight());
			addNewUserStage.setScene(addNewUserScene);
			addNewUserStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void viewIntervention(ActionEvent event) {}
	
	public void resize() {
		AnchorPane.setBottomAnchor(tableAnchor, resources.getScreenHeight() * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, resources.getScreenHeight() * 0.7);
		openNewInterventionButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
		viewInterventionButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
	}
}
