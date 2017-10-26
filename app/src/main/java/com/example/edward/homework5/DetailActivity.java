package com.example.edward.homework5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
        // TODO: update entry object to include updated date & replace release date
        updatedDate.setText(entry.getReleaseDate());
        summary.setText(entry.getSummary());
        Picasso.with(this).load(entry.getLargeImage().trim()).into(imageView);
    }
}
