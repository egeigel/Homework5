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
        return entry.getReleaseDate().compareTo(t1.getReleaseDate());
    }
}
