package utility;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReportBox {
	
	public void displayReport(String title, String report, double stageWidth, double stageHeight) {
		TextArea roadReport = new TextArea();
		roadReport.appendText(report);
		roadReport.setEditable(false);
		roadReport.getStylesheets().add(getClass().getResource("/css/text_area.css").toExternalForm());
		AnchorPane.setBottomAnchor(roadReport, 5.0);
		AnchorPane.setTopAnchor(roadReport, 5.0);
		AnchorPane.setLeftAnchor(roadReport, 5.0);
		AnchorPane.setRightAnchor(roadReport, 5.0);
		
		AnchorPane anchor = new AnchorPane();
		anchor.getStylesheets().add(getClass().getResource("/css/background.css").toExternalForm());
		anchor.setStyle("root2");
		anchor.getChildren().add(roadReport);
		Stage stage = new Stage();
		stage.setTitle("Terenski izvjestaj");
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(new Scene(anchor, stageWidth, stageHeight * 0.5));
		stage.show();
	}
}
