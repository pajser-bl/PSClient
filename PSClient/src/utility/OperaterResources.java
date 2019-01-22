package utility;

import java.util.ArrayList;
import java.util.Enumeration;
import client.FieldTechnician;
import client.Intervention;
import client.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OperaterResources extends ClientResources {
	
	private ArrayList<FieldTechnician> fieldTechnicians;
	private ArrayList<Intervention> interventions;
	private Intervention intervention;
	private Session session;
	
	public OperaterResources(ClientResources resources, ArrayList<FieldTechnician> fieldTechnicians, Session session,
			ArrayList<Intervention> interventions) {
		super(resources.getStage(), resources.getClientCommunication(), resources.getUser(), resources.getScreenHeight(), 
				resources.getScreenHeight());
		this.session = session;
		this.fieldTechnicians = fieldTechnicians;
		this.interventions = interventions;
		intervention = null;
	}
	
	public ArrayList<Intervention> getInterventions() {
		return interventions;
	}

	public void setInterventions(ArrayList<Intervention> interventions) {
		this.interventions = interventions;
	}

	public void setFieldTechnicians(ArrayList<FieldTechnician> fieldTechnicians) {
		this.fieldTechnicians = fieldTechnicians;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public ObservableList<FieldTechnician> getObservableFieldTechnicians() {
		return FXCollections.observableArrayList(fieldTechnicians);
	}
	
	public ObservableList<Intervention> getObservableInterventions(){
		return FXCollections.observableArrayList(interventions);
	}
	
	public ArrayList<FieldTechnician> getFieldTechnicians(){
		return fieldTechnicians;
	}
	
	public Session getSession() {
		return session;
	}
	
	public Intervention getIntervention() {
		return intervention;
	}

	public void setIntervention(Intervention intervention) {
		this.intervention = intervention;
	}

	protected Object handleGetObject(String key) {
		return null;
	}

	public Enumeration<String> getKeys() {
		return null;
	}

}
