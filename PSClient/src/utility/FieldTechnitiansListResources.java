package utility;

import java.util.ArrayList;
import java.util.Enumeration;
import client.FieldTechnitian;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FieldTechnitiansListResources extends ClientResources {
	
	private ArrayList<FieldTechnitian> fieldTechnitians;
	
	public FieldTechnitiansListResources(ClientResources resources, ArrayList<FieldTechnitian> fieldTechnitians) {
		super(resources.getStage(), resources.getClientCommunication(), resources.getUser(), resources.getScreenHeight(), 
				resources.getScreenHeight());
		this.fieldTechnitians = fieldTechnitians;
	}
	
	public ObservableList<FieldTechnitian> getObservableFieldTechnitians() {
		return FXCollections.observableArrayList(fieldTechnitians);
	}
	
	public ArrayList<FieldTechnitian> getFieldTechnitians(){
		return fieldTechnitians;
	}
	
	protected Object handleGetObject(String key) {
		return null;
	}

	public Enumeration<String> getKeys() {
		return null;
	}

}
