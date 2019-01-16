package controller.operater;

import java.util.ArrayList;

import client.FieldTechnitian;
import client.Intervention;
import client.User;
import exception.ServerReplyException;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.ClientResources;
import utility.FieldTechnitiansListResources;
import utility.MessageBox;
import utility.UsersListResources;

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
		try {
			ArrayList<String> reply = resources.getClientCommunication().getFieldTechnicians();
			ArrayList<FieldTechnitian> fieldTechnitians = new ArrayList<>();
			if (reply.get(0).equals("VIEW AVAILABLE FIELD TECHNITIANS OK"))
				if (Integer.parseInt(reply.get(1)) == 0)
					throw new ServerReplyException("Nema slobodnih terenskih radnika");
			for (int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
				String[] parsedUser = reply.get(i + 2).split(":");
				fieldTechnitians.add(new FieldTechnitian(parsedUser[0], parsedUser[1], parsedUser[2], parsedUser[3]));
			}
			Stage newInterventionStage = new Stage();
			ClientResources newResources = new ClientResources(newInterventionStage, resources.getClientCommunication(),
					resources.getUser(), resources.getScreenWidth() * 0.33, resources.getScreenHeight() * 0.7);
			FieldTechnitiansListResources interventionResources = new FieldTechnitiansListResources(newResources, fieldTechnitians);
			newInterventionStage.setResizable(false);
			newInterventionStage.initModality(Modality.APPLICATION_MODAL);
			Parent root = FXMLLoader.load(getClass().getResource("/view/operater/NewInterventionForm.fxml"),
					interventionResources);
			Scene addNewUserScene = new Scene(root, interventionResources.getScreenWidth(),
					interventionResources.getScreenHeight());
			newInterventionStage.setScene(addNewUserScene);
			newInterventionStage.show();
		} catch (ServerReplyException e) {
			MessageBox.displayMessage("Greska", e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void viewIntervention(ActionEvent event) {
	}

	public void resize() {
		AnchorPane.setBottomAnchor(tableAnchor, resources.getScreenHeight() * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, resources.getScreenHeight() * 0.7);
		openNewInterventionButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
		viewInterventionButton.setPrefSize(resources.getScreenHeight() * 0.5, resources.getScreenHeight() * 0.1);
	}
}
