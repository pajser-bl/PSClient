package utility;

import java.time.LocalDateTime;
import java.util.ArrayList;

import client.ClientCommunication;
import controller.fieldTechnician.FieldTechnicianController;
import controller.fieldTechnician.InterventionAlertController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import model.Event;
import model.FieldTechnician;
import model.Intervention;
import model.Session;

public class InterventionHandler extends Task<Void> {

	private AnchorPane optionsAnchor;
	private ClientCommunication clientComm;
	private FieldTechnician user;

	private Session session;
	private Label stateLabel;
	private ImageView stateImage;
	private Intervention intervention;
	private FieldTechnicianController controller;
	private double screenWidth;
	private double screenHeight;

	public InterventionHandler(ClientCommunication clientComm, FieldTechnicianController controller, FieldTechnician user, Session session,
			Intervention intervention, AnchorPane optionsAnchor, Label stateLabel, ImageView stateImage,
			double screenWidth, double screenHeight) {
		this.clientComm = clientComm;
		this.user = user;
		this.controller = controller;
		this.intervention = intervention;
		this.optionsAnchor = optionsAnchor;
		this.stateLabel = stateLabel;
		this.stateImage = stateImage;
		this.session = session;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	protected Void call() throws Exception {
		while (user.getState().equals("aktivan")) {
			ArrayList<String> reply = clientComm.checkOpenedIntervention(user.getId());
			if (reply.get(0).equals("CHECK FIELD TECHNICIAN INTERVENTION OK")) {
				checkOpenedIntervention(reply);
			}
			Thread.sleep(10000);
		}
		return null;
	}

	public void checkOpenedIntervention(ArrayList<String> reply) {
		Platform.runLater(() -> {
			user.setState("zauzet");
			session.changeState(user.getState());
			stateLabel.setText("Stanje: " + user.getState());
			stateLabel.textFillProperty().setValue(Paint.valueOf("ce1919"));
			stateImage.setImage(new Image("/resources/images/red_circle_not_available.png"));
			intervention = new Intervention(reply.get(1), reply.get(2), reply.get(3), reply.get(4), reply.get(5),
					reply.get(6), reply.get(7), reply.get(8), reply.get(9),
					TimeUtility.stringToLocalDateTime(reply.get(10)), reply.get(11), "", LocalDateTime.now(), "", "",
					LocalDateTime.now(), "", "", "", LocalDateTime.now());
			session.newIntervention();
			controller.setIntervention(intervention);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/field_technician/InterventionAlertForm.fxml"));
			System.out.println(intervention.toString());
			loader.setControllerFactory(
					e -> new InterventionAlertController(intervention, screenWidth * 0.3, screenHeight * 0.3));
			try {
				Parent alert = loader.load();
				optionsAnchor.getChildren().add(alert);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
