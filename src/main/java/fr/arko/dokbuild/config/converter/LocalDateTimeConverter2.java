package fr.arko.dokbuild.config.converter;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Converter for database mapping
 */
@Converter(autoApply = false)
public class LocalDateTimeConverter2 implements AttributeConverter<LocalDateTime, String> {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	
	public String convertToDatabaseColumn(LocalDateTime date) {
		return toDate(date);
	}

	public LocalDateTime convertToEntityAttribute(String dbData) {
		return toLocalDateTime(dbData);
	}
	
	public static String toDate(LocalDateTime ldt) {
		return ldt != null ? String.valueOf(ldt) : null;
	}
	
	/**
	 * Le format datetime de sqlite est reçu dans 2 formats différents lors du load ?!
	 * @param date
	 * @return
	 */
	public static LocalDateTime toLocalDateTime(String date) {
		LocalDateTime result = null;
		if (date != null) {
			try {
				result = LocalDateTime.parse(date, formatter);
			} catch (DateTimeException e) {
				
			}
		}
		return result;
	}

}
