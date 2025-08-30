//ethan
package utility;

public class DateUtils {
    private static final String[] VALID_DAYS = {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    public static boolean isValidSchedule(String day, String timeRange) {
        boolean validDay = false;
        for (String validDayStr : VALID_DAYS) {
            if (validDayStr.equalsIgnoreCase(day)) {
                validDay = true;
                break;
            }
        }
        if (!validDay) {
            return false;
        }

        try {
            String[] timeParts = timeRange.split("-");
            if (timeParts.length != 2) {
                return false;
            }
            int startHour = Integer.parseInt(timeParts[0]);
            int endHour = Integer.parseInt(timeParts[1]);
            if (startHour < 0 || startHour > 23 || endHour < 0 || endHour > 23 || startHour >= endHour) {
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
