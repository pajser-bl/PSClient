package controller.operater;

import client.FieldTechnitian;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.FieldTechnitiansListResources;
import utility.UsersListResources;

public class FieldTechnitianTableController {

	@FXML FieldTechnitiansListResources resources;
	@FXML TableView<FieldTechnitian> FieldTechnicianTable;
	
	@FXML public void initialize() {
		TableColumn<FieldTechnitian, String> idColumn = new TableColumn<FieldTechnitian, String>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		TableColumn<FieldTechnitian, String> nameColumn = new TableColumn<FieldTechnitian, String>("Ime");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<FieldTechnitian, String> lastNameColumn = new TableColumn<FieldTechnitian, String>("Prezime");
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		TableColumn<FieldTechnitian, String> stateColumn = new TableColumn<FieldTechnitian, String>("Stanje");
		stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
		FieldTechnicianTable.getColumns().addAll(idColumn, nameColumn, lastNameColumn, stateColumn);
		FieldTechnicianTable.setItems(resources.getObservableFieldTechnitians());
	}
}
