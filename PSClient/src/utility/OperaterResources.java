package utility;

import java.util.ArrayList;
import java.util.Enumeration;
import client.FieldTechnitian;
import client.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OperaterResources extends ClientResources {
	
	private ArrayList<FieldTechnitian> fieldTechnitians;
	private Session session;
	
	public OperaterResources(ClientResources resources, ArrayList<FieldTechnitian> fieldTechnitians, Session session) {
		super(resources.getStage(), resources.getClientCommunication(), resources.getUser(), resources.getScreenHeight(), 
				resources.getScreenHeight());
		this.session = session;
		this.fieldTechnitians = fieldTechnitians;
	}
	
	public ObservableList<FieldTechnitian> getObservableFieldTechnitians() {
		return FXCollections.observableArrayList(fieldTechnitians);
	}
	
	public ArrayList<FieldTechnitian> getFieldTechnitians(){
		return fieldTechnitians;
	}
	
	public Session getSession() {
		return session;
	}
	
	protected Object handleGetObject(String key) {
		return null;
	}

	public Enumeration<String> getKeys() {
		return null;
	}

}
