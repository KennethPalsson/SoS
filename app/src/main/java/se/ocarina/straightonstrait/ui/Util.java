package se.ocarina.straightonstrait.ui;

import android.net.Uri;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class Util {
    private static final long MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1000;
    private static final SimpleDateFormat sTimeFormat = new SimpleDateFormat("HH:mm");

    static long toDuration(String originText, String departureText) {
        long result = toDate(departureText).getTime() - toDate(originText).getTime();
        return result < 0 ? result + MILLISECONDS_PER_DAY : result;
    }

    static Date toDate(String text) {
        try {
            return sTimeFormat.parse(text);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    static String formatTime(Date time) {
        return sTimeFormat.format(time);
    }

    static String formatDuration(long duration) {
        int totalMinutes = (int) (duration / 60 / 1000);
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

    static String getUriQueryParam(String uriText) {
        Uri uri = Uri.parse(uriText);
        return uri.getQueryParameter("ref");
    }

    static String join(String delimiter, List<String> elements) {
        if (elements.isEmpty()) return "";
        StringBuilder sb = new StringBuilder(elements.get(0));
        for (int i = 1; i < elements.size(); i++) {
            sb.append(delimiter);
            sb.append(elements.get(i));
        }
        return sb.toString();
    }
}
