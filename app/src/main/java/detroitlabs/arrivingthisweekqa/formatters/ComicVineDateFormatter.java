package detroitlabs.arrivingthisweekqa.formatters;

import java.util.Calendar;

public class ComicVineDateFormatter extends DateFormatter {
    public ComicVineDateFormatter(String companyId) {
        super(companyId);
    }

    @Override
    protected String baseUrl() {
        return "http://www.comicvine.com/new-comics/?company=%s&startWeek=%s";
    }

    @Override
    protected String dateFormat() {
        return "MM/dd/yyyy";
    }

    @Override
    protected int startOfWeek() {
        return Calendar.SUNDAY;
    }
}