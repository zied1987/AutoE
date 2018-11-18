package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.Logger;

public class DateUtil {
	
	private final static Logger LOGGER = Logger.getLogger(DateUtil.class);

	public Integer compareDate(Date date1, Date date2) {
		if (date1.compareTo(date2) > 0) {
            LOGGER.info("Date1 is after Date2");
            return 1;
        } else if (date1.compareTo(date2) < 0) {
            LOGGER.info("Date1 is before Date2");
            return -1;
        } else if (date1.compareTo(date2) == 0) {
            LOGGER.info("Date1 is equal to Date2");
            return 0;
        } else {
            return null;
        }
	}
	
	public Integer compareDate(String str1, String str2) throws ParseException {
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(str1);
        Date date2 = sdf.parse(str2);
		
		if (date1.compareTo(date2) > 0) {
            LOGGER.info("Date1 is after Date2");
            return 1;
        } else if (date1.compareTo(date2) < 0) {
            LOGGER.info("Date1 is before Date2");
            return -1;
        } else if (date1.compareTo(date2) == 0) {
            LOGGER.info("Date1 is equal to Date2");
            return 0;
        } else {
            return null;
        }
	}

	public String formatDate(Date date, DatePattern pattern) {
		String output;
		SimpleDateFormat formatter;

		Locale currentLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
		formatter = new SimpleDateFormat(pattern.getpattern(), currentLocale);
		date = new Date();
		output = formatter.format(date);
		return output;
	}
	
	public boolean isThisDateValid(String dateToValidate, String dateFromat){
		if(dateToValidate == null){
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		try {
			//if not valid, it will throw ParseException
			sdf.parse(dateToValidate);
		} catch (ParseException e) {
			LOGGER.error(e);
			return false;
		}
		return true;
	}
	
}
