package utility;

import exception.EmptyFieldException;
import exception.ContainsNumberException;
import exception.DateException;

public class Parser {

	public static String parseNewUser(String name, String lastName, String date, String licence, String username, String password) throws Exception {
		if(name.isEmpty() || lastName.isEmpty() || date.isEmpty() || username.isEmpty() || password.isEmpty())
			throw new EmptyFieldException();
		String formatedDate = parseDate(date);
		return formatedDate;
	}
	
	public static String parseDate(String date) throws DateException{
		String[] parsedDate = date.split("\\.");
		if(Integer.parseInt(parsedDate[0]) < 1 || Integer.parseInt(parsedDate[0]) > 31) {
			throw new DateException("Dan mora biti izmedju 1 i 30");
		} else if(Integer.parseInt(parsedDate[1]) < 1 || Integer.parseInt(parsedDate[1]) > 12) {
			throw new DateException("Mjesec mora biti izmedju 1 i 12");
		}
		String formatedDate = parsedDate[2];
		formatedDate += "-" + parsedDate[1] + "-" + parsedDate[0];
		return formatedDate;
	}
	
	public void containsNumber(String name) throws ContainsNumberException{
		if(name.matches(".*\\d.*"));
			throw new ContainsNumberException();
	}
}
