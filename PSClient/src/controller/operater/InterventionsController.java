package controller.operater;

import client.ClientCommunication;
import controller.user.InterventionController;
import exception.MessageException;
import exception.ServerReplyException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.collections.FXCollections;
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
import model.FieldTechnician;
import model.Intervention;
import model.Session;
import model.User;
import utility.MessageBox;
import utility.TimeUtility;

public class InterventionsController {

	private ObservableList<Intervention> interventions;
	private double screenHeight;
	private double screenWidth;
	private ClientCommunication clientComm;
	private Stage mainStage;
	private User user;
	private Session session;
	private Intervention intervention = null;
	@FXML AnchorPane tableAnchor;
	@FXML AnchorPane optionsAnchor;
	@FXML Button openNewInterventionButton;
	@FXML Button showInterventionButton;
	@FXML Button closeInterventionButton;
	@FXML TableView<Intervention> interventionsTable;

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
		interventionsTable.setItems(interventions);
	}
	
	public InterventionsController(Stage mainStage, ClientCommunication clientComm, User user, ObservableList<Intervention> interventions,
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
						fieldTechnicians, session, screenWidth * 0.27, screenHeight * 0.7));
				Parent newInterventionView = loader.load();
				newInterventionStage.getIcons().add(new Image("/resources/images/logo.png"));
				Scene addNewUserScene = new Scene(newInterventionView, screenWidth * 0.27, screenHeight * 0.7);
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
		try {
			if(interventionsTable.getSelectionModel().isEmpty())
				throw new MessageException("Odaberite intervenciju");
			String interventionId = interventionsTable.getSelectionModel().getSelectedItem().getId();
			ArrayList<String> reply = clientComm.viewOpenedIntervention(interventionId);
			if(reply.get(0).equals("VIEW INTERVENTION OK")) {
				if(reply.get(11).equals("otvorena"))
					intervention = new Intervention(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5),
							reply.get(6), reply.get(7), reply.get(8), reply.get(9),
							TimeUtility.stringToLocalDateTime(reply.get(10)), reply.get(11), "", 
							LocalDateTime.now(), "", "", LocalDateTime.now(), "", "", "", LocalDateTime.now());
				else
					intervention = new Intervention(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5),
						reply.get(6), reply.get(7), reply.get(8), reply.get(9),
						TimeUtility.stringToLocalDateTime(reply.get(10)), reply.get(11), reply.get(12), 
						TimeUtility.stringToLocalDateTime(reply.get(13)), reply.get(14), "",
						LocalDateTime.now(), "", "", "", LocalDateTime.now());
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/InterventionForm.fxml"));
				loader.setControllerFactory(e -> new InterventionController(intervention, user.getType(),
						screenWidth * 0.25, screenHeight * 0.6));
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
			System.out.println(e.getClass().toString());
			e.printStackTrace();
		}
	}
	
	public void closeIntervention(ActionEvent event) {
		try {
			if(interventionsTable.getSelectionModel().isEmpty())
				throw new MessageException("Odaberite intervenciju");
			if(!interventionsTable.getSelectionModel().getSelectedItem().getState().equals("terenski izvjestaj"))
				throw new MessageException("Intervencija nema terenski izvjestaj");
			Stage reportStage = new Stage();
			reportStage.getIcons().add(new Image("/resources/images/logo.png"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/operater/CloseInterventionForm.fxml"));
			loader.setControllerFactory(e -> new CloseInterventionController(reportStage, clientComm, user, session, interventions, 
					interventionsTable.getSelectionModel().getSelectedItem()));
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
	

	public void resize() {
		AnchorPane.setBottomAnchor(tableAnchor, screenHeight * 0.1);
		AnchorPane.setTopAnchor(optionsAnchor, screenHeight * 0.7);
	}
}
