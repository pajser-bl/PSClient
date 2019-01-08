package new_forms;

import java.time.LocalDateTime;

import client.Intervention;
import client.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utility.ClientResources;

public class NewInterventionsController {

	@FXML AnchorPane tablePane;
	@FXML AnchorPane optionsPane;
	@FXML Button openNewInterventionButton;
	@FXML Button viewInterventionButton;
	@FXML TableView<Intervention> interventionsTable;
	@FXML ClientResources resources;
	
	@FXML public void initialize() {
		TableColumn<Intervention, String> idColumn = new TableColumn<Intervention, String>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<Intervention, String> clientColumn = new TableColumn<Intervention, String>("Klijent");
		clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
		TableColumn<Intervention, String> userOpenedColumn = new TableColumn<Intervention, String>("Operater");
		userOpenedColumn.setCellValueFactory(new PropertyValueFactory<>("userOpened"));
		TableColumn<Intervention, LocalDateTime> openedOnColumn = new TableColumn<Intervention, LocalDateTime>("Vrijeme");
		interventionsTable.getColumns().addAll(idColumn, clientColumn, userOpenedColumn, openedOnColumn);
		resize();
	}
	
	public void openNewIntervention(ActionEvent event) {}
	public void viewIntervention(ActionEvent event) {}
	
	public void resize() {
		AnchorPane.setBottomAnchor(tablePane, resources.getScreenHeight() * 0.1);
		AnchorPane.setTopAnchor(optionsPane, resources.getScreenHeight() * 0.7);
		openNewInterventionButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
		viewInterventionButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
	}
}
