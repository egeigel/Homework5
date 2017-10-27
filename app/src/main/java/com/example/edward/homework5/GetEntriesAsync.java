package com.example.edward.homework5;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by edward on 10/23/17.
 */

public class GetEntriesAsync extends AsyncTask<String, Void, ArrayList<Entry>> {
    ArrayList<Entry> entries;
    MainActivity activity;
    ProgressDialog pd;

    public GetEntriesAsync(MainActivity activity) {
        this.activity = activity;
        this.pd = new ProgressDialog(activity);
    }

    //TODO: Set up progress dialog
    @Override
    protected void onPreExecute() {
        pd.setMessage("Loading News...");
        pd.show();
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Entry> doInBackground(String... strings) {
        entries = new ArrayList<>();
        HttpURLConnection connection = null;
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                entries = FeedParser.FeedSaxParser.parseFeed(connection.getInputStream());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return entries;
    }

    @Override
    protected void onPostExecute(ArrayList<Entry> entries) {
        super.onPostExecute(entries);
        activity.setSources(entries);
        if (pd != null) {
            // update activity
            pd.dismiss();
        }
    }
}
