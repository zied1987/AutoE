package string;

import java.util.regex.Pattern;

public class StringUtils {

	
	public boolean isNullorEmpty(String str) {
		return (str.isEmpty() || str == null) ;
	}
	
	public boolean isEqual(String str1, String str2) {
		if (str1 == null) {
            return (str2 == null);
		} else {
            return str1.equals(str2);
		}
	}
	
	public String capitalizeFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	
	public String ConcatinateStringsWithSeparator(String separator, String... str ) {
		String result ="";
		for(String mot : str) {
			result = result+mot+separator;
		}
		int ind = result.lastIndexOf(separator);
		return  new StringBuilder(result).replace(ind, ind+1,"").toString();
	}
	
	public static String ConcatinateStringsWithoutSeparator(String... str ) {
		String result ="";
		for(String mot : str) {
			result = result+mot;
		}
		return  result;
	}
	
	public boolean matchPattern(String str, String patern) {
		return Pattern.matches(patern, str);
	}
	
	public boolean matchPatternOnlyString(String str, String patern) {
		return Pattern.matches("[A-Za-z]", str);
	}
	
	public boolean matchPatternOnlyNumbers(String str, String patern) {
		return Pattern.matches("\\d", str);
	}
	
	public int numberofWords(String str) {
	    int wordCount = 0;

	    boolean word = false;
	    int endOfLine = str.length() - 1;

	    for (int i = 0; i < str.length(); i++) {
	        if (Character.isLetter(str.charAt(i)) && i != endOfLine) {
	            word = true;
	        } else if (!Character.isLetter(str.charAt(i)) && word) {
	            wordCount++;
	            word = false;
	        } else if (Character.isLetter(str.charAt(i)) && i == endOfLine) {
	            wordCount++;
	        }
	    }
	    return wordCount;
	}

}