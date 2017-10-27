package com.example.edward.homework5;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

import static android.R.attr.key;

public class MainActivity extends AppCompatActivity {

    ArrayList<Entry> entries;
    private ListView listView;
    private PodcastAdapter adapter;
    public static final String ENTRY_OBJECT_KEY = "entry";
    Button goButton;
    Button clearButton;
    EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button)findViewById(R.id.goButton);
        searchBar = (EditText)findViewById(R.id.searchBar);
        clearButton = (Button)findViewById(R.id.clearButton);

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.sort(new EntryComparator(searchBar.getText().toString()));
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: point of this text?
                searchBar.setText("Clear List Search");
                adapter.notifyDataSetChanged();
            }
        });

        if(isConnected()) {
            try {
                entries = new GetEntriesAsync(this).execute("https://itunes.apple.com/us/rss/toppodcasts/limit=30/xml").get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }

    public void setSources(final ArrayList<Entry> sources){
        listView = (ListView) findViewById(R.id.listView);
        adapter = new PodcastAdapter(this, R.layout.list_item_layout2, sources);
        adapter.sort(new DateComparator());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Entry s = sources.get(position);
                Intent goToDetail = new Intent(MainActivity.this, DetailActivity.class);
                goToDetail.putExtra(ENTRY_OBJECT_KEY, s);
                startActivity(goToDetail);
            }
        });

        for(int i = 0; i < adapter.getCount()-1; i++){
            Log.d("debug", adapter.getItem(i).getReleaseDate().toString());
        }
    }



}