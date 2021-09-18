package data_time;



import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;





public class DateTimeApi {
    /**
     * Return the current date as a String depending on a query.
     * <p>
     * The query can be passed for the whole date or for it's part:
     * - FULL - current date as a whole: year, month, day of month
     * formatted as `YYYY-MM-DD` (a default return value);
     * - YEAR - current year;
     * - MONTH - name of the current month;
     * - DAY - current day of month;
     * In any other case throw DateTimeException.
     **/
    public String todayDate(DateTimePart datePart) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter;
        switch (datePart) {
            case FULL:
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return localDateTime.format(formatter);
            case DAY:
                formatter = DateTimeFormatter.ofPattern("dd");
                return localDateTime.format(formatter);
            case YEAR:
                formatter = DateTimeFormatter.ofPattern("yyyy");
                return localDateTime.format(formatter);
            case MONTH:
                formatter = DateTimeFormatter.ofPattern("MMMM");
                return localDateTime.format(formatter).toUpperCase();
            case HOURS:
                formatter = DateTimeFormatter.ISO_LOCAL_TIME;
                return localDateTime.format(formatter);
            default:
                throw new DateTimeException("DateTimeException");
        }
    }

    /**
     * Given an Array of 3 elements, where
     * - 1-st element is a `year`;
     * - 2-nd element is s `month`;
     * - 3-rd element is a `day of month`;
     * <p>
     * Return Optional of a date built from these elements.
     */
    public Optional<LocalDate> getDate(Integer[] dateParams) {
        if (dateParams.length == 0) {
            return Optional.empty();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer temp : dateParams) {
            stringBuilder.append(temp).append("-");
        }
        return Optional.of(LocalDate.parse(stringBuilder.substring(0, stringBuilder.length() - 1)));
    }

    /**
     * Given the time and the number of hours to add, return the changed time.
     */
    public LocalTime addHours(LocalTime localTime, Integer hoursToAdd) {
        return LocalTime.from(localTime).plusHours(hoursToAdd);
    }

    /**
     * Given the time and the number of minutes to add, return the changed time.
     */
    public LocalTime addMinutes(LocalTime localTime, Integer minutesToAdd) {
        return LocalTime.from(localTime).plusMinutes(minutesToAdd);
    }

    /**
     * Given the time and the number of seconds to add, return the changed time.
     */
    public LocalTime addSeconds(LocalTime localTime, Integer secondsToAdd) {
        return LocalTime.from(localTime).plusSeconds(secondsToAdd);
    }

    /**
     * Given the date and the number of weeks to add, return the changed date.
     */
    public LocalDate addWeeks(LocalDate localDate, Integer numberOfWeeks) {
        return LocalDate.from(localDate).plusWeeks(numberOfWeeks);
    }

    /**
     * Given a random `someDate` date, return one of the following Strings:
     * - "`someDate` is after `currentDate`"
     * if `someDate` is in the future relating to the `current date`;
     * - "`someDate` is before `currentDate`"
     * if `someDate` is in the past relating to the `current date`;
     * - "`someDate` is today"
     * if `someDate` is today;
     */
    public String beforeOrAfter(LocalDate someDate) {
        LocalDate localDate = LocalDate.now();
        if (localDate.isAfter(someDate)) {
            return someDate + " is before " + localDate;
        }
        if (localDate.isBefore(someDate)) {
            return someDate + " is after " + localDate;
        }

        return someDate + " is today";
    }

    /**
     * Given a String representation of a date and some timezone,
     * return LocalDateTime in this timezone.
     */
    public LocalDateTime getDateInSpecificTimeZone(String dateInString, String zone) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateInString);
        return LocalDateTime.ofInstant(zonedDateTime.toInstant(), ZoneId.of(zone).getRules().getOffset(LocalDateTime.now()));
    }

    /**
     * Given some LocalDateTime, return an OffsetDateTime with the local time offset applied
     * (`+02:00` for Ukraine).
     * <p>
     * Example: we receive a LocalDateTime with a value `2019-09-06T13:17`.
     * We should return the OffsetDateTime with a value `2019-09-06T13:17+02:00`,
     * where `+02:00` is the offset for our local timezone.
     * <p>
     * OffsetDateTime is recommended to use for storing date values in a database.
     */
    public OffsetDateTime offsetDateTime(LocalDateTime localTime) {
        return OffsetDateTime.of(localTime, ZoneOffset.of("+02:00"));
    }

    /**
     * Given a String formatted as `yyyymmdd`,
     * return Optional of this date as a LocalDate.
     */
    public Optional<LocalDate> parseDate(String date) {
        String years = date.substring(0, 4);
        String month = date.substring(4, 6);
        String days = date.substring(6, 8);
        String resultData = years + "-" + month + "-" + days;
        int i1 = Integer.parseInt(month);
        if (i1 <= 12) {
            LocalDate localDate = LocalDate.parse(resultData);
            return Optional.of(localDate);
        }
        return Optional.empty();
    }

    /**
     * Given a String formatted as `d MMM yyyy` (MMM - Sep, Oct, etc),
     * return Optional of this date as a LocalDate.
     */
    public Optional<LocalDate> customParseDate(String date) {
        try {
            return Optional.ofNullable(LocalDate.parse(date, DateTimeFormatter.ofPattern("d MMM yyyy")));
        } catch (DateTimeException e) {
            return Optional.empty();
        }

    }

    /**
     * Given some LocalDateTime, return a String formatted as
     * `day(2-digit) month(full name in English) year(4-digit) hours(24-hour format):minutes`.
     * <p>
     * Example: "01 January 2000 18:00".
     */
    public String formatDate(LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm").format(dateTime);
    }
}
