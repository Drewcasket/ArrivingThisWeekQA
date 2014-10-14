package detroitlabs.arrivingthisweekqa.formatters;

import java.util.Calendar;

public class ComixologyDateFormatter extends DateFormatter {
    public ComixologyDateFormatter(String companyId) {
        super(companyId);
    }

    @Override
    protected String baseUrl() {
        return "http://pulllist.comixology.com/%s/%s";
    }

    @Override
    protected String dateFormat() {
        return "yyyy/MM/dd";
    }

    @Override
    protected int startOfWeek() {
        return Calendar.WEDNESDAY;
    }
}
