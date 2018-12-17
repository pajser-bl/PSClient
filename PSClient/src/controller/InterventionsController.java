package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import utility.ClientResources;

public class InterventionsController {
	
	@FXML AnchorPane anchor;
	@FXML TableView interventionsTable;
	@FXML BorderPane mainLayout;
	@FXML ClientResources resources;
	
	
	@FXML public void initialize() {
		anchor.setMaxHeight(resources.getScreenHeight());
		anchor.setMaxWidth(resources.getScreenWidth());
		mainLayout.setMaxHeight(resources.getScreenHeight() * 0.5);
		mainLayout.setMaxWidth(resources.getScreenWidth() * 0.5);
	}
}
