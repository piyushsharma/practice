package com.tosort;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

	public Date getCompanyWeekStartDate(Date inputDate) {
		Date date = GenericDateUtil.trim(inputDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		Date weekStartDate = GenericDateUtil.addDaysToDate(date, -(dayOfWeek % 7));
		return weekStartDate;
	}

	public int getCompanyWeekIdFromDate(Date inputDate) {
		Date date = GenericDateUtil.trim(inputDate);
		int companyYear = getCompanyYearFromDate(date);
		long totalTimeSinceCompanyYearBeginning = date.getTime() - getCompanyYearStartDate(companyYear).getTime();
		float totalDays = (float)totalTimeSinceCompanyYearBeginning / GenericDateUtil.milliSecondsInDays;
		totalDays = GenericDateUtil.round(totalDays, 1);
		int weekNumber = (int)(totalDays/7) + 1;
		return (companyYear * 100 + weekNumber);
	}

	/* Return first day of the company year */
    /* Refer: [Company Calendar Week](http://www.trendresults.com/retail-link-documents/WalMartCalendarOverview.pdf) */
	private Date getCompanyYearStartDate(int year) {
        /* Company Week Starts on Saturday at midnight 12.00am and ends on Friday night at 11:59pm.
        * First week of Company Year starts on the Week containing February 1st.
        * Thus, Company Week 01 usually contains a few last days of January
        * Each quarter equals 13 weeks (4+5+4).
        * Company Year usually consist of 52 or 53 weeks
        * */
		Date feb01 = GenericDateUtil.getDateFromCalendar(year, Calendar.FEBRUARY, 1);
		Calendar cal = Calendar.getInstance();
        /* Get day of week */
		cal.setTime(feb01);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        /* Get first day of year */
		Date firstDateOfYear = GenericDateUtil.addDaysToDate(feb01, -(dayOfWeek % 7));
		return firstDateOfYear;
	}

	public int getCompanyYearFromDate(Date date) {
		int calYear = GenericDateUtil.getCalYearFromDate(date);
		Date firstDateOfCompanyYear = getCompanyYearStartDate(calYear);
		if(firstDateOfCompanyYear.getTime() > date.getTime()) {
			calYear -= 1;
		}
		return calYear;
	}

	public Date getCompanyWeekStartDateFromCompanyWeekId(int companytWeekId) {
		int companytYear = companytWeekId / 100;
		int companytWeekNo = companytWeekId % 100;
		Date yearStartDate = getCompanyYearStartDate(companytYear);
		return GenericDateUtil.addDaysToDate(yearStartDate, (companytWeekNo - 1) * 7);
	}

	public void generateData() {
         /* Code to generate data file with 10 years of Company Calendar for US */
		Date startDate = GenericDateUtil.getStringAsDate("2015-01-01");
		Date endDate = GenericDateUtil.getStringAsDate("2020-12-31");

		try{
			File file = new File("datedata/calendar.csv");
            /* If File does not exists, then create it */
			if(!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter;
			fileWriter = new FileWriter(file.getName());
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);

			while(!startDate.after(endDate)) {
				Calendar c = Calendar.getInstance();
				c.setTime(startDate);
				String line = GenericDateUtil.getDateAsString(startDate)
					+ "," + GenericDateUtil.getDayOfWeek(c.get(Calendar.DAY_OF_WEEK));

				Date weekStartDate = getCompanyWeekStartDate(startDate);
				c.setTime(weekStartDate);
				Date weekEndDate = GenericDateUtil.getDateFromCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH) + 6);

				int companyYear = getCompanyYearFromDate(startDate);
				int companyWeekId = getCompanyWeekIdFromDate(startDate);
				int weekNo = companyWeekId - companyYear * 100;

				line += "," + GenericDateUtil.getDateAsString(weekStartDate)
					+ "," + GenericDateUtil.getDateAsString(weekEndDate)
					+ "," + Integer.toString(weekNo)
					+ "," + Integer.toString(companyYear)
					+ "," + Integer.toString(companyWeekId)
					+ "\n";

				bufferWriter.write(line);

				c.setTime(startDate);
				startDate = GenericDateUtil.getDateFromCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH) + 1);
			}
			bufferWriter.close();
		} catch(IOException e) {
			throw new RuntimeException("Caught IO Exception!", e);
		}
	}

	public static void main(String args[]) {
        /* Test Cases for Us Calendar end week numbers */
		String bDate[] = new String[]{
			"2012-01-28", "2013-01-25",
			"2013-01-26", "2014-01-31",
			"2014-02-01", "2015-01-30",
			"2015-01-31", "2016-01-29",
			"2016-01-30", "2016-06-12",
			"2017-01-28", "2018-01-26",
			"2018-01-27", "2019-01-25",
			"2019-01-26", "2020-01-31",
			"2020-02-01", "2021-01-29",
			"2021-01-30", "2022-01-28",
		};
		DateUtils w = new DateUtils();
		for (String sd : bDate) {
			Date startDate = GenericDateUtil.getStringAsDate(sd);
			System.out.println("date => " + sd);
			System.out.println(w.getCompanyWeekIdFromDate(startDate));
			System.out.println(w.getCompanyWeekStartDate(startDate));
			System.out.println("============================");
		}

		w.generateData();
	}
}
