package com.example.edward.homework5;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by mariah on 10/24/17.
 */

public class PodcastAdapter extends ArrayAdapter<Entry> {

    Context context;

    public PodcastAdapter(Context context, int resource, List<Entry> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entry a = getItem(position);
        ViewHolder viewHolder;

        if(convertView == null){ //if no view to re-use then inflate a new one
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout2, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.titleView);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.picture);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(a.getTitle().contains(((MainActivity)context).searchBar.getText().toString())){
            convertView.setBackgroundColor(Color.GREEN);
        }
        else{
            convertView.setBackgroundColor(Color.WHITE);
        }

        //set the data from the object
        Log.d("demo" , a.getSmallImage());
        viewHolder.tvTitle.setText(a.getTitle());
        Picasso.with(context).load(a.getSmallImage().trim()).into(viewHolder.imageView);
        return convertView;
    }

    //View Holder to cache the views
    private static class ViewHolder{
        TextView tvTitle;
        TextView tvDate;
        TextView tvAuthor;
        ImageView imageView;
    }
}
