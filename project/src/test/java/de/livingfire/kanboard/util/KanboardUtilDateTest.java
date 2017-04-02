/*
* ----------------------------------------------------------------------------
* license (Revision 42):
* As long as you retain this notice and apply common sense you can do whatever
* you want with this stuff.             Thomas Lutz, http://github.com/phoen1x
* ----------------------------------------------------------------------------
*/
package de.livingfire.kanboard.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.Test;

import de.livingfire.kanboard.exception.KanboardException;

public class KanboardUtilDateTest {

    @Test
    public void testConvertToDateKanboard() throws Exception {
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Paris");

        GregorianCalendar calendar = new GregorianCalendar(2016, 3, 1);
        calendar.setTimeZone(timeZone);

        String actual = new KanboardUtilDate(timeZone).convertToDateKanboard(calendar.getTime());
        assertThat(actual, is("2016-04-01"));
    }

    @Test
    public void testConvertToDateJava() throws Exception {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        KanboardUtilDate util = new KanboardUtilDate(timeZone);
        String dateExpected = "1476028904";

        Date dateActual = util.convertToDateJava(dateExpected);

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeZone(timeZone);
        calendar.setTime(dateActual);

        assertThat(calendar.get(Calendar.DAY_OF_MONTH), is(9));
        assertThat(calendar.get(Calendar.MONTH), is(9));
        assertThat(calendar.get(Calendar.YEAR), is(2016));
        assertThat(calendar.get(Calendar.HOUR_OF_DAY), is(16));
        assertThat(calendar.get(Calendar.MINUTE), is(1));
        assertThat(calendar.get(Calendar.SECOND), is(44));
        assertThat(calendar.get(Calendar.MILLISECOND), is(0));

        String dateIllegal = null;
        try {
            util.convertToDateJava(dateIllegal);
            fail("Expected Exception to be thrown");
        } catch (KanboardException e) {
            assertThat(e.getMessage(), is("illegal date: null. Valid dates are Strings containing 10 digits."));
        }

        dateIllegal = "1476029210000";
        try {
            util.convertToDateJava(dateIllegal);
            fail("Expected Exception to be thrown");
        } catch (KanboardException e) {
            assertThat(
                    e.getMessage(),
                        is("illegal date: 1476029210000. Valid dates are Strings containing 10 digits."));
        }

        dateIllegal = "aaaaaaaaaa";
        try {
            util.convertToDateJava(dateIllegal);
            fail("Expected Exception to be thrown");
        } catch (KanboardException e) {
            assertThat(e.getMessage(), is("illegal date: aaaaaaaaaa. Valid dates are Strings containing 10 digits."));
        }
    }

    @Test
    public void testGetDateFormatDebug() throws Exception {
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Berlin");

        GregorianCalendar calendar = new GregorianCalendar(2016, 3, 1);
        calendar.setTimeZone(timeZone);
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.MONTH, 9);
        calendar.set(Calendar.YEAR, 2016);
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 1);
        calendar.set(Calendar.SECOND, 44);
        calendar.set(Calendar.MILLISECOND, 523);

        DateFormat format = new KanboardUtilDate(timeZone).getDateFormatDebug();
        assertThat(format.format(calendar.getTime()), equalTo("2016-10-03 16:01:44.523 +0200 CEST"));
    }

    @Test
    public void testGetDateFormat() throws Exception {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");

        GregorianCalendar calendar = new GregorianCalendar(2016, 3, 1);
        calendar.setTimeZone(timeZone);

        DateFormat format = new KanboardUtilDate(timeZone).getDateFormat("dd.MM.YYYY HH:mm");
        assertThat(format.format(calendar.getTime()), equalTo("01.04.2016 00:00"));
    }

}
