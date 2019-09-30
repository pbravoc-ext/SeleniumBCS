package cl.bcs.application.file.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	public static String fecha() {
		LocalDateTime hora = LocalDateTime.now();
		DateTimeFormatter f = DateTimeFormatter.ofPattern("ddMMYYYYhhmm");
		return hora.format(f);
	}
	
	}

