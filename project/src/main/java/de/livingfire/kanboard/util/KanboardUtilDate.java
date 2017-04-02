package de.livingfire.kanboard.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import de.livingfire.kanboard.exception.KanboardException;

public class KanboardUtilDate {

    public static final String FORMAT_KANBOARD = "yyyy-MM-dd";
    public static final String FORMAT_DEBUG = "yyyy-MM-dd HH:mm:ss.SSS Z z";

    private final SimpleDateFormat kanboardFormat;

    private final TimeZone timeZone;

    public KanboardUtilDate(TimeZone timeZone) {
        super();
        this.timeZone = timeZone;
        this.kanboardFormat = new SimpleDateFormat(FORMAT_KANBOARD);
        this.kanboardFormat.setTimeZone(timeZone);
    }

    public KanboardUtilDate(String timeZone) {
        this(TimeZone.getTimeZone(timeZone));
    }

    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public String convertToDateKanboard(Date date) {
        return this.kanboardFormat.format(date);
    }

    public Date convertToDateJava(String date) {
        if ((date == null) || !date.matches("^[0-9]{1,10}$")) {
            throw new KanboardException("illegal date: " + date + ". Valid dates are Strings containing 10 digits.");
        }
        return new Date(Long.valueOf(date + "000"));
    }

    public DateFormat getDateFormatDebug() {
        return getDateFormat(FORMAT_DEBUG);
    }

    public DateFormat getDateFormat(String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(this.timeZone);
        return format;
    }
}
