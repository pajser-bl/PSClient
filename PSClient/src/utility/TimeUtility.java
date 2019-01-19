package utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtility {

	public static String getStringTimeNow() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(LocalDateTime.now());
	}

	public static String localDateTimeToDDmmYYYY(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
		return formatter.format(localDateTime);
	}

	public static String localDateTimeToHHmm(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		return formatter.format(localDateTime);
	}

	public static LocalDateTime stringToLocalDateTime(String stringDateTime) {
		if (stringDateTime == null)
			return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(stringDateTime, formatter);
	}

	public static String localDateTimeToString(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return formatter.format(localDateTime);
	}

	public static LocalDate stringToLocalDate(String stringDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(stringDate, formatter);
	}
	public static String localDateToString(LocalDate localDate) {
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return formatter.format(localDate);
		
	}
	
}