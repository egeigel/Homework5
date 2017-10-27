package com.example.edward.homework5;

import java.util.Comparator;

/**
 * Created by edward on 10/26/17.
 */

public class EntryComparator implements Comparator<Entry> {

    String key;
    public EntryComparator(String key){
        this.key = key;
    }

    @Override
    public int compare(Entry entry, Entry t1) {
        if(entry.getTitle().contains(key) && !t1.getTitle().contains(key)){
            return -1;
        }
        else if(!entry.getSummary().contains(key) && t1.getSummary().contains(key)){
            return 1;
        }
        return 0;
    }
}
