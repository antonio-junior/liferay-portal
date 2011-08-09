/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.kernel.util;

import java.text.DateFormat;
import java.text.Format;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Brian Wing Shun Chan
 */
public class DateUtil {

	public static final String ISO_8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";

	public static int compareTo(Date date1, Date date2) {
		return compareTo(date1, date2, false);
	}

	public static int compareTo(
		Date date1, Date date2, boolean ignoreMilliseconds) {

		// Workaround for bug in JDK 1.5.x. This bug is fixed in JDK 1.5.07. See
		// http://bugs.sun.com/bugdatabase/view_bug.do;:YfiG?bug_id=6207898 for
		// more information.

		if ((date1 != null) && (date2 == null)) {
			return -1;
		}
		else if ((date1 == null) && (date2 != null)) {
			return 1;
		}
		else if ((date1 == null) && (date2 == null)) {
			return 0;
		}

		long time1 = date1.getTime();
		long time2 = date2.getTime();

		if (ignoreMilliseconds) {
			time1 = time1 / Time.SECOND;
			time2 = time2 / Time.SECOND;
		}

		if (time1 == time2) {
			return 0;
		}
		else if (time1 < time2) {
			return -1;
		}
		else {
			return 1;
		}
	}

	public static boolean equals(Date date1, Date date2) {
		if (compareTo(date1, date2) == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean equals(
		Date date1, Date date2, boolean ignoreMilliseconds) {

		if (!ignoreMilliseconds) {
			return equals(date1, date2);
		}

		long deltaTime = date1.getTime() - date2.getTime();

		if ((deltaTime > -1000) && (deltaTime < 1000)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static String getCurrentDate(String pattern, Locale locale) {
		return getDate(new Date(), pattern, locale);
	}

	public static String getCurrentDate(
		String pattern, Locale locale, TimeZone timeZone) {

		return getDate(new Date(), pattern, locale, timeZone);
	}

	public static String getDate(Date date, String pattern, Locale locale) {
		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			pattern, locale);

		return dateFormat.format(date);
	}

	public static String getDate(
		Date date, String pattern, Locale locale, TimeZone timeZone) {

		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			pattern, locale, timeZone);

		return dateFormat.format(date);
	}

	public static int getDaysBetween(
		Date startDate, Date endDate, TimeZone timeZone) {

		int offset = timeZone.getRawOffset();

		Calendar startCal = new GregorianCalendar(timeZone);

		startCal.setTime(startDate);
		startCal.add(Calendar.MILLISECOND, offset);

		Calendar endCal = new GregorianCalendar(timeZone);

		endCal.setTime(endDate);
		endCal.add(Calendar.MILLISECOND, offset);

		int daysBetween = 0;

		while (CalendarUtil.beforeByDay(startCal.getTime(), endCal.getTime())) {
			startCal.add(Calendar.DAY_OF_MONTH, 1);

			daysBetween++;
		}

		return daysBetween;
	}

	public static DateFormat getISOFormat() {
		return getISOFormat(StringPool.BLANK);
	}

	public static DateFormat getISOFormat(String text) {
		String pattern = StringPool.BLANK;

		if (text.length() == 8) {
			pattern = "yyyyMMdd";
		}
		else if (text.length() == 12) {
			pattern = "yyyyMMddHHmm";
		}
		else if (text.length() == 13) {
			pattern = "yyyyMMdd'T'HHmm";
		}
		else if (text.length() == 14) {
			pattern = "yyyyMMddHHmmss";
		}
		else if (text.length() == 15) {
			pattern = "yyyyMMdd'T'HHmmss";
		}
		else if ((text.length() > 8) && (text.charAt(8) == 'T')) {
			pattern = "yyyyMMdd'T'HHmmssz";
		}
		else {
			pattern = "yyyyMMddHHmmssz";
		}

		return DateFormatFactoryUtil.getSimpleDateFormat(pattern);
	}

	public static DateFormat getISO8601Format() {
		return DateFormatFactoryUtil.getSimpleDateFormat(ISO_8601_PATTERN);
	}

	public static DateFormat getUTCFormat() {
		return getUTCFormat(StringPool.BLANK);
	}

	public static DateFormat getUTCFormat(String text) {
		String pattern = StringPool.BLANK;

		if (text.length() == 8) {
			pattern = "yyyyMMdd";
		}
		else if (text.length() == 12) {
			pattern = "yyyyMMddHHmm";
		}
		else if (text.length() == 13) {
			pattern = "yyyyMMdd'T'HHmm";
		}
		else if (text.length() == 14) {
			pattern = "yyyyMMddHHmmss";
		}
		else if (text.length() == 15) {
			pattern = "yyyyMMdd'T'HHmmss";
		}
		else {
			pattern = "yyyyMMdd'T'HHmmssz";
		}

		return DateFormatFactoryUtil.getSimpleDateFormat(
			pattern, TimeZoneUtil.getTimeZone(StringPool.UTC));
	}

	public static Date newDate() {
		return new Date();
	}

	public static Date newDate(long date) {
		return new Date(date);
	}

}