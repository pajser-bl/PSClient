package controller.operater;

import client.ClientCommunication;
import exception.ServerReplyException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.collections.FXCollections;
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
import model.FieldTechnician;
import model.Intervention;
import model.Session;
import model.User;
import utility.MessageBox;

public class InterventionsController {

	private ArrayList<Intervention> interventions;
	private double screenHeight;
	private double screenWidth;
	private ClientCommunication clientComm;
	private Stage mainStage;
	private User user;
	private Session session;
	@FXML
	AnchorPane tableAnchor;
	@FXML
	AnchorPane optionsAnchor;
	@FXML
	Button openNewInterventionButton;
	@FXML
	Button viewInterventionButton;
	@FXML
	TableView<Intervention> interventionsTable;

	@FXML
	public void initialize() {
		resize();
		TableColumn<Intervention, String> idColumn = new TableColumn<Intervention, String>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
		TableColumn<Intervention, String> clientColumn = new TableColumn<Intervention, String>("Klijent");
		clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
		TableColumn<Intervention, String> operaterColumn = new TableColumn<Intervention, String>("Operater");
		operaterColumn.setCellValueFactory(new PropertyValueFactory<>("userOpened"));
		TableColumn<Intervention, LocalDateTime> openedOnColumn = new TableColumn<Intervention, LocalDateTime>("Vrijeme otvaranja");
		openedOnColumn.setCellValueFactory(new PropertyValueFactory<>("openedOn"));
		TableColumn<Intervention, String> fieldTechnicianColumn = new TableColumn<Intervention, String>("Terenski radnik");
		fieldTechnicianColumn.setCellValueFactory(new PropertyValueFactory<>("fieldTechnician"));
		TableColumn<Intervention, String> stateColumn = new TableColumn<Intervention, String>("Stanje");
		stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
		interventionsTable.getColumns().addAll(idColumn, clientColumn, operaterColumn, openedOnColumn,
				fieldTechnicianColumn, stateColumn);
		interventionsTable.setItems(FXCollections.observableArrayList(interventions));
	}
	
	public InterventionsController(Stage mainStage, ClientCommunication clientComm, User user, ArrayList<Intervention> interventions,
			Session session, double screenWidth, double screenHeight) {
		this.mainStage = mainStage;
		this.clientComm = clientComm;
		this.user = user;
		this.interventions = interventions;
		this.session = session;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	public void openNewIntervention(ActionEvent event) {
		try {
			ArrayList<String> reply = clientComm.getAvailableFieldTechnicians();
			ArrayList<FieldTechnician> fieldTechnicians = new ArrayList<>();
			if (reply.get(0).equals("VIEW AVAILABLE FIELD TECHNICIANS OK") && Integer.parseInt(reply.get(1)) == 0)
				throw new ServerReplyException("Nema slobodnih terenskih radnika");
			if (reply.get(0).equals("VIEW AVAILABLE FIELD TECHNICIANS OK")) {
				for (int i = 0; i < Integer.parseInt(reply.get(1)); i++) {
					String[] parsedUser = reply.get(i + 2).split(":");
					fieldTechnicians.add(new FieldTechnician(parsedUser[0], parsedUser[1], parsedUser[2], parsedUser[3]));
				}
				Stage newInterventionStage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/operater/NewInterventionForm.fxml"));
				loader.setControllerFactory(e -> new NewInterventionController(newInterventionStage, clientComm, user,
						fieldTechnicians, session, screenWidth * 0.33, screenHeight * 0.7));
				Parent newInterventionView = loader.load();
				Scene addNewUserScene = new Scene(newInterventionView, screenWidth * 0.33, screenHeight * 0.7);
				newInterventionStage.setScene(addNewUserScene);
				newInterventionStage.setResizable(false);
				newInterventionStage.initModality(Modality.APPLICATION_MODAL);
				newInterventionStage.show();
			}
		} catch (ServerReplyException e) {
			MessageBox.displayMessage("Greska", e.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showIntervention(ActionEvent event) {
		String interventionId = interventionsTable.getSelectionModel().getSelectedItem().getId();
		ArrayList<String> reply = clientComm.viewOpenedIntervention(interventionId);
		if(reply.get(0).equals("VIEW OPENED INTERVENTION OK")) {
			//Intervention intervention = new Intervention(reply.get(1));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/operater/InterventionForm.fxml"));
			Stage interventionStage = new Stage();
			interventionStage.setResizable(false);
			interventionStage.initModality(Modality.APPLICATION_MODAL);
		}
	}

	public void resize() {
		AnchorPane.setBottomAnchor(tableAnchor, screenHeight * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, screenHeight * 0.7);
		openNewInterventionButton.setPrefSize(screenHeight * 0.5, screenHeight * 0.1);
		viewInterventionButton.setPrefSize(screenHeight * 0.5, screenHeight * 0.1);
	}
}
