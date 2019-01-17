package utility;

import java.util.ArrayList;
import java.util.Enumeration;
import client.FieldTechnician;
import client.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OperaterResources extends ClientResources {
	
	private ArrayList<FieldTechnician> fieldTechnitians;
	private Session session;
	
	public OperaterResources(ClientResources resources, ArrayList<FieldTechnician> fieldTechnitians, Session session) {
		super(resources.getStage(), resources.getClientCommunication(), resources.getUser(), resources.getScreenHeight(), 
				resources.getScreenHeight());
		this.session = session;
		this.fieldTechnitians = fieldTechnitians;
	}
	
	public ObservableList<FieldTechnician> getObservableFieldTechnitians() {
		return FXCollections.observableArrayList(fieldTechnitians);
	}
	
	public ArrayList<FieldTechnician> getFieldTechnitians(){
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
