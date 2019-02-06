package controller.fieldTechnician;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import model.Intervention;

public class InterventionAlertController {

	private AnchorPane optionsAnchor;
	private double stageHeight;
	private double stageWidth;
	private Intervention intervention;
	@FXML AnchorPane mainAnchor;
	@FXML ImageView upperEdge;
	@FXML ImageView lowerEdge;
	@FXML VBox optionsBox;
	
	@FXML public void initialize() {
		resize();
	}
	
	public InterventionAlertController(AnchorPane optionsAnchor, double stageWidth, double stageHeight) {
		this.optionsAnchor = optionsAnchor;
		this.stageWidth = stageWidth;
		this.stageHeight = stageHeight;
	}
	
	public void openIntervention(ActionEvent event) {
		optionsAnchor.getChildren().remove(optionsBox.getParent());
	}
	
	public void resize() {
		mainAnchor.setPrefSize(stageWidth, stageHeight);
		AnchorPane.setLeftAnchor(optionsBox, stageWidth * 0.5);
		upperEdge.setFitWidth(stageWidth);
		lowerEdge.setFitWidth(stageWidth);
	}
}
