package upc.bdam.recommender.neo4j;

public class Values {
	
	public static String getPhoneNumber() {
		String number = "6";
		for (int i = 1; i < 9; i++) {
			number += (int)(Math.random()*10);
		}
		return number;
	}
	
	public static String getURL() {
		String url = "http://";
		for (int i = 1; i <= 4; i++) {
			url += (int)(Math.random()*255)+1;
			if (i < 4) {
				url += '.';
			}
		}
		return url;
	}
	
	public static int getAge() {
		return (int)(Math.random()*69)+15;
	}
	
	public static String getPhone() {
		int brand = (int)(Math.random()*4);
		switch (brand) {
			case 0:
				return "samsung";
			case 1:
				return "htc";
			case 2:
				return "nokia";
			case 3:
				return "iphone";
			default:
				return "";
		}
	}
	
	public static double getCallPrice() {
		return (int)((Math.random()*8+2)*100)/(double)100;
	}
	
	public static double getCallDuration() {
		return (int)((Math.random()*59+1)*100)/(double)100;
	}
	
	public static double getTextPrice() {
		return (int)(Math.random()*100)/(double)100;
	}
	
	public static double getTextLength() {
		return (int)((Math.random()*999+1)*100)/(double)100;
	}
	
	public static String getDay() {
		int day = (int)(Math.random()*7);
		switch (day) {
			case 0:
				return "monday";
			case 1:
				return "tuesday";
			case 2:
				return "wednesday";
			case 3:
				return "thursday";
			case 4:
				return "friday";
			case 5:
				return "saturday";
			case 6:
				return "sunday";
			default:
				return "";
		}
	}
	
}
