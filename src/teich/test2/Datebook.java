package teich.test2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Datebook holds Events
 * 
 * 
 * You can obtain the day of week, day of month and day of year for a particular
 * Date by using the following code.
 * 
 * Date date = ... ; Calendar calendar = Calendar.getInstance();
 * calendar.setTime(date); int dayOf = calendar.get(field);
 * 
 * Where field is one of Calendar.DAY_OF_YEAR, Calendar.DAY_OF_MONTH,
 * Calendar.DAY_OF_WEEK
 * 
 * Refer to the code in DatebookTest on how to construct a Date object.
 * 
 * Refer to documentation on the Calendar class
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 * 
 */

public class Datebook {

	Map<Date, Event> dateBook;
	List<Date> dates;

	public Datebook() {
		dateBook = new HashMap<Date, Event>();
		dates = new ArrayList<Date>();
	}

	/**
	 * Add a single Event to the Datebook for a particular date. This is a
	 * non-recurring event.
	 * 
	 * @param event
	 * @param date
	 */
	public void addSingleEvent(Event event, Date date) {
		dateBook.put(date, event);
		dates.add(date);
	}

	/**
	 * Adds an Event to a Datebook that is recurring every day. For instance, a
	 * wake up alarm.
	 */
	public void addDailyEvent(Event event) {
		Calendar calendar = Calendar.getInstance();

		int day = 25;
		int month = Calendar.NOVEMBER;
		int year = 2014;
		for (int i = year; i < 9999; i++) {

			if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
					&& day <= 30) {
				day++;
			} else if ((month == 4 || month == 6 || month == 9 || month == 11) && day <= 29) {
				day++;
			} else if (month == 2 && day <= 27) {
				day++;
			} else {
				day = 1;
				if (month < 12) {
					month++;
				} else {
					month = 1;
				}
			}
			calendar.set(year, month, day, 0, 0, 0);
			Date date = calendar.getTime();

			dateBook.put(date, event);
			dates.add(date);
		}
	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every week.
	 * For instance, a class starts at the same time once a week.
	 * 
	 * @param dayOfWeek
	 *            This is a constant from the Calendar class. (ex.
	 *            Calendar.MONDAY, Calendar.TUESDAY...)
	 * 
	 */
	public void addWeeklyEvent(Event event, int dayOfWeek) {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		int dayOfMnth = Math.abs((cal.get(Calendar.DAY_OF_WEEK) - dayOfWeek) - 1);
		int date = 1 + dayOfMnth;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		for (int i = 0; i < 5; i++) {
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), date, 0, 0, 0);
			date += 7;
			Date day = calendar.getTime();
			if (!(i == 4 && date < 30)) {
				dateBook.put(day, event);
				dates.add(day);
			}
		}
	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every month.
	 * For instance, you always get paid on the 1st and the 15th of the month.
	 * 
	 * @param dayOfMonth
	 *            this is the day of the month starting with 1
	 */
	public void addMonthlyEvent(Event event, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < 12; i++) {
			calendar.set(2014, i, dayOfMonth, 0, 0, 0);
			Date date = calendar.getTime();
			dateBook.put(date, event);
			dates.add(date);
		}
	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every year.
	 * For instance, a birthday.
	 * 
	 * @param dayOfYear
	 *            this is the day of the year starting with 1 and ending with
	 *            365
	 */
	public void addYearlyEvent(Event event, int dayOfYear) {
		Calendar cal = Calendar.getInstance();

		for (int y = 2014; y < 9999; y++) {
			cal.set(y, cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			cal.set(Calendar.DAY_OF_YEAR, dayOfYear);
			Date date = cal.getTime();
			dateBook.put(date, event);
			dates.add(date);
		}
	}

	/**
	 * 
	 * @return a List of Events for the specified date. The Events should be
	 *         sorted by their timeOfDay. If no events occur on that day then an
	 *         empty List should be returned.
	 */
	public List<Event> getEvents(Date date) {
		List<Event> list = new ArrayList<Event>();
		SimpleDateFormat formatter = new SimpleDateFormat("MM.dd.yyyy");
		for (Date d : dates) {

			String day = formatter.format(date);
			String day2 = formatter.format(d);

			if (day.equals(day2)) {
				list.add(dateBook.get(d));
			}
		}
		Collections.sort(list);
		return list;
	}

}
