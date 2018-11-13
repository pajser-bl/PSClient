package utility;

import exception.EmptyFieldException;
import exception.ContainsNumberException;
import exception.NotANumberException;

public class Parser {

	public static String parseNewUser(String name, String lastName, String date, String licence, String username, String password) throws Exception {
		if(name.isEmpty() || lastName.isEmpty() || date.isEmpty() || username.isEmpty() || password.isEmpty())
			throw new EmptyFieldException();
		String formatedDate = parseDate(date);
		return formatedDate;
	}
	
	public static String parseDate(String date) throws NotANumberException{
		String[] parsedDate = date.split("\\.");
		for(int i = 0; i < 3; i++)
			Integer.parseInt(parsedDate[i]);
		String formatedDate = parsedDate[2];
		formatedDate += "-" + parsedDate[1] + "-" + parsedDate[0];
		return formatedDate;
	}
	
	public void containsNumber(String name) throws ContainsNumberException{
		if(name.matches(".*\\d.*"));
			throw new ContainsNumberException();
	}
}
