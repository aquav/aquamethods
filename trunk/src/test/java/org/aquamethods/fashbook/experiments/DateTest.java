package org.aquamethods.fashbook.experiments;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		new DateTest().getEventFormDate();
		System.out.print(new DateTest().getEventFormDate());
	}

	private Date getEventFormDate() throws Exception{
		String date = "2012-11-28";
		String hour = "05";
		String min = "15";
		String ampm = "am";
		
		String dateString =  date +" "+hour+":"+min+":"+00+" "+ampm;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd h:mm:ss a", Locale.US);
		
		Date returnDate = format.parse(dateString);
		
		return returnDate;
	}
}
