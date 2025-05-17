package utils

import java.text.SimpleDateFormat
import java.util.TimeZone

public class DateTimeUtil {

	/**
	 * Return current UTC time in format required by qTest API.
	 * Example: "2025-04-24T10:04:36.131Z"
	 */
	static String getQTestDateTimeFormat() {
		Date now = new Date()
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"))
		return sdf.format(now)
	}
}
