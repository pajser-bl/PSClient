package controller.supervisor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SessionController {

	private double stageHeight;
	private double stageWidth;
	private String id;
	private String user;
	private String start;
	private String end;
	private String session;
	@FXML Label idLabel;
	@FXML Label userLabel;
	@FXML Label startLabel;
	@FXML Label endLabel;
	@FXML TextArea sessionsText;
	@FXML VBox infoBox;
	@FXML VBox labelBox;
	
	@FXML public void initialize() {
		idLabel.setText(id);
		userLabel.setText(user);
		startLabel.setText(start);
		endLabel.setText(end);
		sessionsText.setText(session);
		resize();
	}
	
	public SessionController(String id, String user, String start, String end, String session, double stageWidth,
			double stageHeight) {
		this.id = id;
		this.user = user;
		this.start = start;
		this.end = end;
		this.session = session;
		this.stageWidth = stageWidth;
		this.stageHeight = stageHeight;
	}
	
	public void resize() {
		AnchorPane.setBottomAnchor(labelBox, stageHeight * 0.7);
		AnchorPane.setRightAnchor(labelBox, stageWidth * 0.5);
		AnchorPane.setBottomAnchor(infoBox, stageHeight * 0.7);
		AnchorPane.setLeftAnchor(infoBox, stageWidth * 0.5);
		AnchorPane.setTopAnchor(sessionsText, stageHeight * 0.32);
	}
}
