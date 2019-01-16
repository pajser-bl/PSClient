package utility;

import client.Event;
import client.FieldTechnician;
import client.Intervention;
import client.User;
import client.Session;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TestingClass {

	public static ArrayList<User> generateUsers() {
		ArrayList<User> users = new ArrayList<User>();
		User user1 = new User("1", "Marko", "Markovic", "Administrator", "marko");
		User user2 = new User("2", "Ivan", "Ivanovic", "Supervizor", "ivan");
		User user3 = new User("3", "Jovana", "Jovanic", "Operater", "jovana");
		User user4 = new User("4", "Opasno", "Dosadno", "Terenski Radnik", "radnik");
		users.add(user1); users.add(user2); users.add(user3); users.add(user4); 
		return users;
	}
	
	public static ObservableList<Intervention> generateInterventions() {
		ArrayList<Intervention> interventions = new ArrayList<Intervention>();
		Intervention intervention1 = new Intervention("1", "1", "1", "1", "1", LocalDateTime.now(), LocalDateTime.now(), true, "Asd.");
		Intervention intervention2 = new Intervention("2", "2", "2", "2", "2", LocalDateTime.now(), LocalDateTime.now(), true, "Aasd.");
		Intervention intervention3 = new Intervention("3", "3", "3", "3", "3", LocalDateTime.now(), LocalDateTime.now(), true, "Aaasd.");
		Intervention intervention4 = new Intervention("4", "4", "4", "4", "4", LocalDateTime.now(), LocalDateTime.now(), true, "Aaaasd.");
		Intervention intervention5 = new Intervention("5", "5", "5", "5", "5", LocalDateTime.now(), LocalDateTime.now(), true, "Assssd.");
		Intervention intervention6 = new Intervention("6", "6", "6", "6", "6", LocalDateTime.now(), LocalDateTime.now(), true, "Asddddsd.");
		interventions.add(intervention1); interventions.add(intervention2); interventions.add(intervention3); interventions.add(intervention4);
		interventions.add(intervention5); interventions.add(intervention6); interventions.add(intervention6); interventions.add(intervention6);
		interventions.add(intervention6); interventions.add(intervention6); interventions.add(intervention6); interventions.add(intervention6);
		interventions.add(intervention6); interventions.add(intervention6); interventions.add(intervention6); interventions.add(intervention6);
		interventions.add(intervention6); interventions.add(intervention6); interventions.add(intervention6); interventions.add(intervention6);
		interventions.add(intervention6); interventions.add(intervention6); interventions.add(intervention6); interventions.add(intervention6);
		interventions.add(intervention6); interventions.add(intervention6); interventions.add(intervention6); interventions.add(intervention6);
		return FXCollections.observableArrayList(interventions);
	}
	
	public static Session generateSession() {
		Event event1 = new Event("Otvorena nova intervencija; Id: 1");
		Event event2 = new Event("Zatvorena intervencija; Id: 1");
		Session session = new Session();
		session.getEventList().add(event1);
		session.getEventList().add(event2);
		return session;
	}
	
	public static ObservableList<FieldTechnician> generateFieldTechnicians(){
		FieldTechnician ft1 = new FieldTechnician("1", "Janko", "Jankovic", "dostupan");
		FieldTechnician ft2 = new FieldTechnician("2", "Marinko", "Marinkovic", "zauzet");
		FieldTechnician ft3= new FieldTechnician("3", "PaDokle", "Vise", "nedostupan");
		ArrayList<FieldTechnician> fts = new ArrayList<>();
		fts.add(ft1); fts.add(ft2); fts.add(ft3);
		return FXCollections.observableArrayList(fts);
	}
}
