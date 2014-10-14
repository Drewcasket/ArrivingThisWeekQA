package detroitlabs.arrivingthisweekqa.formatters;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateFormatter {
    private final String companyId;

    public DateFormatter(String companyId) {
        this.companyId = companyId;
    }

    public String lastWeek() {
        return String.format(baseUrl(), companyId, determineDate(-7));
    }

    public String nextWeek() {
        return String.format(baseUrl(), companyId, determineDate(7));
    }

    public String currentWeek() {
        String currentDate;
        Calendar now = Calendar.getInstance();
        int dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == startOfWeek()) {
            currentDate = new SimpleDateFormat(dateFormat()).format(new Date());
        } else {
            int difference = (startOfWeek() - dayOfWeek) % 7;
            now.add(Calendar.DAY_OF_WEEK, difference);
            currentDate = new SimpleDateFormat(dateFormat()).format(now.getTime());
        }
        return String.format(baseUrl(), companyId, currentDate);
    }

    private String determineDate(int daysToAdd) {
        String date;
        Calendar now = Calendar.getInstance();
        int dayOfWeek = now.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == startOfWeek()) {
            now.add(Calendar.DAY_OF_MONTH, daysToAdd);
            date = new SimpleDateFormat(dateFormat()).format(now.getTime());
        } else {
            int difference = (startOfWeek() - dayOfWeek) % 7;
            now.add(Calendar.DAY_OF_WEEK, difference);
            now.add(Calendar.DAY_OF_MONTH, daysToAdd);
            date = new SimpleDateFormat(dateFormat()).format(now.getTime());
        }
        return date;
    }

    protected abstract String baseUrl();

    protected abstract String dateFormat();

    protected abstract int startOfWeek();
}
