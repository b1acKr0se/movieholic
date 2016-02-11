package b1ackr0se.io.movieholic.util;

import java.util.Calendar;

/**
 * Utility class to provide appropriate date
 */
public class DateUtil {

    private Calendar calendar;

    public DateUtil() {
        calendar = Calendar.getInstance();
    }

    public String getFirstDateString() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month + "-" + day;
    }

    public String getSecondDateString() {
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month + "-" + day;
    }
}
