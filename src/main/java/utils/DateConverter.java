package utils;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Helper class for Date conversion
 */
public class DateConverter {
    /**
     * Method for converting a String to local date
     * @param date the sting that is to be converted
     * @return the converted LocalDate
     */
    public static LocalDate convertStringToLocalDate(String date) {
        String[] array = date.split("-");
        LocalDate result = LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]),
                Integer.parseInt(array[2]));
        return result;
    }

    /**
     * Method for converting a String to LocalTime
     * @param time the sting that is to be converted
     * @return the converted LocalTime
     */
    public static LocalTime convertStringToLocalTime(String time) {
        String[] array = time.split(":");
        LocalTime result = LocalTime.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
        return result;
    }
}
