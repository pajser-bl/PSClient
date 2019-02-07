package controller.fieldTechnician;

import controller.user.InterventionController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Intervention;

public class InterventionAlertController {

	private double stageHeight;
	private double stageWidth;
	private Intervention intervention;
	@FXML AnchorPane mainAnchor;
	@FXML Button viewButton;
	@FXML ImageView upperEdge;
	@FXML ImageView lowerEdge;
	@FXML VBox optionsBox;
	
	@FXML public void initialize() {
		resize();
	}
	
	public InterventionAlertController(Intervention intervention, double stageWidth, double stageHeight) {
		this.intervention = intervention;
		this.stageWidth = stageWidth;
		this.stageHeight = stageHeight;
	}
	
	public void openIntervention(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/InterventionForm.fxml"));
		loader.setControllerFactory(e -> new InterventionController(intervention, "Field technician", stageWidth, stageHeight));
		try {
			Parent root = loader.load();
			Stage interventionStage = new Stage();
			interventionStage.setResizable(false);
			interventionStage.initModality(Modality.APPLICATION_MODAL);
			interventionStage.setScene(new Scene(root, stageWidth * 1, stageHeight * 2.3));
			interventionStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void resize() {
		mainAnchor.setPrefSize(stageWidth, stageHeight);
		AnchorPane.setLeftAnchor(optionsBox, stageWidth * 0.5);
		upperEdge.setFitWidth(stageWidth);
		lowerEdge.setFitWidth(stageWidth);
		viewButton.setPrefSize(stageWidth * 0.3, stageHeight * 0.3);
	}
}
