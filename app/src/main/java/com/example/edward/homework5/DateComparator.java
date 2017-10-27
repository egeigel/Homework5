package com.example.edward.homework5;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by mariah on 10/26/17.
 */

public class DateComparator implements Comparator<Entry> {

    @Override
    public int compare(Entry entry, Entry t1) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            Date d = format.parse(entry.getReleaseDate().trim());
            Date d1 = format.parse(t1.getReleaseDate().trim());
            return d.compareTo(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
