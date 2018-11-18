package date;

public enum DatePattern {

	ddMMyy ("dd.MM.yy"),
	yyyyMMddGathhmmssz ("yyyy.MM.dd G 'at' hh:mm:ss z"),
	EEEMMMdyy ("EEE, MMM d, ''yy"),
	hmma ("h:mm a"),
	Hmm ("H:mm"),
	HmmssSSS ("H:mm:ss:SSS"),	
	Kmmaz ("K:mm a,z"),
	yyyyMMMMMddGGGhhmmaaa ("yyyy.MMMMM.dd GGG hh:mm aaa");
	   
	  private String pattern;
	   
	  DatePattern(String pat){
	    this.pattern = pat;
	  }
	   
	  public String getpattern(){
	    return pattern;
	  }
	
}