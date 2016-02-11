package b1ackr0se.io.movieholic.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Utility class to provide appropriate date
 */
public class DateUtil {

    private Calendar calendar;

    public DateUtil() {
        calendar = Calendar.getInstance();
    }

    public String getFirstDateString() {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return simpleDateFormat.format(calendar.getTime());
    }

    public String getSecondDateString() {
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        return simpleDateFormat.format(calendar.getTime());
    }
}
