package com.example.edward.homework5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.sephiroth.android.library.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView summary;
    private TextView title;
    private TextView updatedDate;
    private Entry entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // set entry object
        entry = (Entry) getIntent().getSerializableExtra(MainActivity.ENTRY_OBJECT_KEY);

        imageView = (ImageView)findViewById(R.id.imageView);
        summary = (TextView)findViewById(R.id.summaryContentView);
        title = (TextView)findViewById(R.id.titleView);
        updatedDate = (TextView)findViewById(R.id.updatedDateView);

        // update view
        title.setText(entry.getTitle());
        // TODO: update entry object to include updated date & use that date below
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(entry.getUpdatedDate().trim());
            String formattedDate = new SimpleDateFormat("MM/dd/yyyy HH:mm aaa").format(date);
            updatedDate.setText("Last Updated: " + formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //updatedDate.setText(entry.getReleaseDate());
        summary.setText(entry.getSummary());
        Picasso.with(this).load(entry.getLargeImage().trim()).into(imageView);
    }
}
