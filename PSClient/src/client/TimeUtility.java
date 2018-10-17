package client;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtility {
	public static LocalDateTime getLDTNow() {
		return LocalDateTime.now();
	}

	public static String formatDate(LocalDateTime localDateTime) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.YYYY");
		return simpleDateFormat.format(localDateTime);
	}

	public static String formatTime(LocalDateTime localDateTime) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
		return simpleDateFormat.format(localDateTime);
	}

	public static String formatTimeDate(LocalDateTime localDateTime) {
		return formatDate(localDateTime) + " " + formatTime(localDateTime);
	}
	
	public static String localDateTimeToDBString(LocalDateTime localDateTime) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		return simpleDateFormat.format(localDateTime);
	}
	public static LocalDateTime stringToLocalDateTime(String stringDateTime) {
		if(stringDateTime==null)return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime localDateTime = LocalDateTime.parse(stringDateTime, formatter);
		return localDateTime;
	}
	
	public static LocalDate stringToLocalDate(String stringDate) {
		if(stringDate==null)return null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(stringDate, formatter);
		return localDate;
	}
	
	public static String localDateToDBString(LocalDate localDate) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
		return simpleDateFormat.format(localDate);
	}
}