package utils;

import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.validator.UrlValidator;
import org.apache.log4j.Logger;

import string.StringUtils;


public class Validators {

	private final static Logger LOGGER = Logger.getLogger(Validators.class);
	
	public boolean validateEmail(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
	}
		
	public boolean validateCodePostale(String plz) {		
		return Pattern.matches("\\d{4}", plz);
	}
	
	/** 
	 * Accept: 21222222 ou 21 222 222 ou 21-222-222
	 * @param numTel
	 * @return
	 */
	public boolean validateNumTel(String numTel) {	
		return Pattern.matches("\\d{8}|\\d{2} \\d{3} \\d{3}|\\d{2}-\\d{3}-\\d{3}", numTel);
	}
	

	public boolean validateAdresseInternet(String siteWeb) {
		UrlValidator urlValidator = new UrlValidator();

	    //valid URL
	    if(!siteWeb.contains("http://"))
	    	siteWeb = StringUtils.ConcatinateStringsWithoutSeparator("http://"+siteWeb);
	    
	    LOGGER.info(siteWeb);
	    if (urlValidator.isValid(siteWeb)) {
	       LOGGER.info("url is valid");
	       return true;
	    } else {
	       LOGGER.error("url is invalid");
	       return false;
	    }
	}
	
}
