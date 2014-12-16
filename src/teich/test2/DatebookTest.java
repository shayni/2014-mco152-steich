package teich.test2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DatebookTest {

	/**
	 * 
	 * @param year
	 *            4 digit year
	 * @param month
	 *            Calendar.JANUARY, Calendar.FEBRUARY...
	 * @param dayOfMonth
	 *            starting from 1
	 * @return A Date from the specified parameters
	 */
	private Date getDate(int year, int month, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month, dayOfMonth, 0, 0, 0);
		return calendar.getTime();
	}

	@Test
	/**
	 * After calling addSingleEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddSingleEvent() {
		Datebook datebook = new Datebook();

		// given an event
		Event event = new Event("EVENT 1", 1200);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);

		// when the event is added today
		datebook.addSingleEvent(event, today);

		// then the event is returned for today
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

		// then the event is not returned tomorrow
		Date tomorrow = getDate(2014, Calendar.NOVEMBER, 26);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	/**
	 * After calling addYearlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddYearlyEvent() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, 5);
		Datebook d = new Datebook();
		Event event = new Event("test", 1200);
		d.addYearlyEvent(event, 5);
		List<Event> list = d.getEvents(getDate(2014, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_YEAR)));
		Assert.assertNotNull(list);
		Assert.assertSame(event, list.get(0));
	}

	@Test
	/**
	 * After calling addMonthlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddMonthlyEvent() {
		Datebook d = new Datebook();
		Event event = new Event("test", 1200);
		d.addMonthlyEvent(event, 5);
		List<Event> list = d.getEvents(getDate(2014, 3, 5));
		Assert.assertNotNull(list);
		Assert.assertSame(event, list.get(0));
	}

	@Test
	/**
	 * After calling addWeeklyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddWeeklyEvent() {
		Datebook d = new Datebook();
		Event event = new Event("test2", 1200);
		d.addWeeklyEvent(event, Calendar.TUESDAY);
		List<Event> list = d.getEvents(getDate(2014, 11, 23));
		Assert.assertNotNull(list);
		Assert.assertSame(event, list.get(0));
	}

	@Test
	/**
	 * After calling addDailyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddDailyEvent() {
		Datebook dateBook = new Datebook();

		Event event = new Event("alarm", 0000);

		dateBook.addDailyEvent(event);
		Date today = getDate(2014, Calendar.NOVEMBER, 26);

		List<Event> l = new ArrayList<Event>();
		l = dateBook.getEvents(today);
		Assert.assertNotNull(l);

		Assert.assertSame(event, l.get(0));
		Assert.assertSame(event, l.get(10));
	}

	@Test
	/**
	 * After adding multiple Events, verify that they are all returned from getEvents() in the correct order.
	 */
	public void testGetEventsReturnsSortedList() {
		Datebook dateBook = new Datebook();

		Event event = new Event("alarm1", 0000);
		Event e2 = new Event("alarm2", 1200);
		Event e3 = new Event("alarm3", 1300);

		dateBook.addWeeklyEvent(event, 2);
		dateBook.addWeeklyEvent(e2, 2);
		dateBook.addWeeklyEvent(e3, 2);

		List<Event> events = dateBook.getEvents(getDate(2014, 11, 29));

		Assert.assertNotNull(events);
		Assert.assertSame(event, events.get(0));
		Assert.assertSame(e2, events.get(1));
		Assert.assertSame(e3, events.get(2));
	}

}
