package cn.edu.buaa.jsi.psmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class XMLParser {

	public static long isoTimeParse(String timeValue) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		StringBuilder sb = new StringBuilder(timeValue);
		sb.replace(sb.indexOf(":") + 6, sb.indexOf("UTC") - 1, "");
		Date date = simpleDateFormat.parse(sb.toString());
		return date.getTime();
	}
	
}
