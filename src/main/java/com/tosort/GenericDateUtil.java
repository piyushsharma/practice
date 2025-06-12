package com.tosort;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Piyush Sharma
 * @date 3/23/18
 */

public class GenericDateUtil {

	private static Logger logger = LoggerFactory.getLogger(GenericDateUtil.class);

	public static final int milliSecondsInDays = 1000 * 60 * 60 * 24;
	public static final int daysInWeek = 7;

	private GenericDateUtil() {
	}

	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

	public static String getDateAsString(Date date) {
		String sDate = null;
		if (date == null) {
			return sDate;
		}
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			sDate = df.format(date);
		} catch (IllegalArgumentException ex) {
			logger.error("Invalid Date Object. Unable to format.");
		}
		return sDate;
	}

	public static Date getStringAsDate(String sDate) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(sDate);
		} catch (ParseException e) {
			logger.error("Could not parse string %s to date object", sDate);
		}
		return date;
	}

	public static String getDayOfWeek(int value) {
		String day = "";
		switch (value) {
			case 1:
				day = "Sunday";
				break;
			case 2:
				day = "Monday";
				break;
			case 3:
				day = "Tuesday";
				break;
			case 4:
				day = "Wednesday";
				break;
			case 5:
				day = "Thursday";
				break;
			case 6:
				day = "Friday";
				break;
			case 7:
				day = "Saturday";
				break;
		}
		return day;
	}

	public static String getDayNameFromDate(Date curDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(curDate);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return getDayOfWeek(dayOfWeek);
	}

	public static String getDayNameFromString(String curDate) {
		Date date = getStringAsDate(curDate);
		return getDayNameFromDate(date);
	}

	/* To make sure that we do not have any time stored in the input date
	 * Time can cause the date comparison to behave incorrectly
	 */
	public static Date trim(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}

	/* Set Date given year, month and day of month */
	public static Date getDateFromCalendar(int year, int month, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, dayOfMonth);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		return calendar.getTime();
	}

	public static Date addDaysToDate(Date dt, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, days);
		dt = c.getTime();
		return dt;
	}


	public static int getCalYearFromDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	public static Timestamp getCurrentTimestamp() {
		Calendar calendar = Calendar.getInstance();
		return new java.sql.Timestamp(calendar.getTime().getTime());
	}

	public static SimpleDateFormat getIsoDateFormat() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}

	public static Date getMonthStartDate(String dateAsString) {
		Date date;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		try {
			date = df.parse(dateAsString);

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));

			return c.getTime();
		} catch (ParseException pe) {
			throw new RuntimeException("Parameter date should be of the format 'yyyy-mm-dd'");
		}
	}

	public static String getMonthStartDateAsStr(String dateAsString) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(GenericDateUtil.getMonthStartDate(dateAsString));
	}

	public static Date getMonthEndDate(String dateAsString) {
		Date date;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		try {
			date = df.parse(dateAsString);

			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

			return c.getTime();
		} catch (ParseException pe) {
			throw new RuntimeException("Parameter date should be of the format 'yyyy-mm-dd'");
		}
	}

	public static String getMonthEndDateAsStr(String dateAsString) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(GenericDateUtil.getMonthEndDate(dateAsString));
	}

	/*
	   This is a helper method to convert time to PST time for 'Order Drop Time' and 'Updated At'
	   column in excel sheet (planner or supply planner download sheet).

	   Order Drop Time actually uses created_at column in workflow_documents_v2 table. That column
	   is defined as 'timestamp without timezone'. But when that db column is populated, that time
	   value is UTC time. But when documentState is retrieved local timezone is automatically added,
	   which is PST, this cause inconsistency here. In order to solve this issue, needs to convert
	   the datetime into string, then parse the string with UTC time zone.

	   Similarly with 'Updated At' field. That column is also defined as 'timestamp without timezone',
	   so needs to apply the same logic.
	*/
	public static Date utcToPstTime(Date dateTime) {
		return convertTimeBetweenTimeZones(dateTime, "UTC", "PST");
	}

	/*
	   This is a helper method to convert a date time which does not have a time zone, convert from one
	   time zone to another time zone.

	   If can not do the conversion, return null.
	 */
	public static Date convertTimeBetweenTimeZones(Date dateTime, String sourceTimeZone, String destinationTimeZone) {
		try {
			DateFormat destinationTimeZoneFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			destinationTimeZoneFormat.setTimeZone(TimeZone.getTimeZone(destinationTimeZone));

			DateFormat sourceTimeZoneFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			sourceTimeZoneFormat.setTimeZone(TimeZone.getTimeZone(sourceTimeZone));

			return sourceTimeZoneFormat.parse(destinationTimeZoneFormat.format(dateTime));
		} catch (Exception e) {
			logger.error("Error in converting UTC time to PST time: ", e.toString());
		}
		return null;
	}

}
