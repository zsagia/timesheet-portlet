package com.liferay.timesheet.util;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class TimesheetUtilTest {

	@Test
	public void testAddDateToDate() {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		long time = calendar.getTimeInMillis();

		Date today = new Date(time);

		Calendar calendar2 = Calendar.getInstance();

		calendar2.setTimeInMillis(0);
		calendar2.set(Calendar.HOUR, 8);
		calendar2.set(Calendar.MINUTE, 30);

		long time2 = calendar2.getTimeInMillis();

		Date todayTime = new Date(time2);

		Date date = DateTimeUtil.addDateToDate(today, todayTime);

		Assert.assertEquals(
			calendar.getTimeInMillis() + calendar2.getTimeInMillis(),
			date.getTime());
	}

	@Test
	public void testAddTimeToDate() throws Exception {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		long time = calendar.getTimeInMillis();

		Date today = new Date(time);

		Calendar calendar2 = Calendar.getInstance();

		calendar2.setTimeInMillis(0);
		calendar2.set(Calendar.HOUR, 8);
		calendar2.set(Calendar.MINUTE, 30);

		long time2 = calendar2.getTimeInMillis();

		Date date = DateTimeUtil.addTimeToDate(today, time2);

		Assert.assertEquals(
			calendar.getTimeInMillis() + calendar2.getTimeInMillis(),
			date.getTime());
	}

	@Test
	public void testGetTodayWithoutTime() throws Exception {
		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date date = DateTimeUtil.getTodayWithoutTime();

		long time1 = calendar.getTimeInMillis();
		long time2 = date.getTime();

		Assert.assertEquals(time1, time2);
	}
}