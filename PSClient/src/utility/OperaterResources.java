package utility;

import java.util.ArrayList;
import java.util.Enumeration;
import client.FieldTechnician;
import client.Intervention;
import client.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OperaterResources extends ClientResources {
	
	private ArrayList<FieldTechnician> fieldTechnitians;
	private ArrayList<Intervention> interventions;
	private Session session;
	
	public OperaterResources(ClientResources resources, ArrayList<FieldTechnician> fieldTechnitians, Session session,
			ArrayList<Intervention> interventions) {
		super(resources.getStage(), resources.getClientCommunication(), resources.getUser(), resources.getScreenHeight(), 
				resources.getScreenHeight());
		this.session = session;
		this.fieldTechnitians = fieldTechnitians;
		this.interventions = interventions;
	}
	
	public ArrayList<Intervention> getInterventions() {
		return interventions;
	}

	public void setInterventions(ArrayList<Intervention> interventions) {
		this.interventions = interventions;
	}

	public void setFieldTechnitians(ArrayList<FieldTechnician> fieldTechnitians) {
		this.fieldTechnitians = fieldTechnitians;
	}

	public void setSession(Session session) {
		this.session = session;
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
