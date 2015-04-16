package com.andreidiaconu.transitions.stage8;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.andreidiaconu.transitions.R;
import com.squareup.picasso.Picasso;

/**
 * Created by andrei on 4/14/2015.
 */
public class GridActivity8 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ((Toolbar) findViewById(R.id.action_bar)).setTitle("Grid - Stage 8");

        GridView grid = (GridView) findViewById(R.id.grid);
        String[] data = getResources().getStringArray(R.array.cats_array);
        ImagesAdapter adapter = new ImagesAdapter(this, data);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imageUrl = ((ImagesAdapter)parent.getAdapter()).getItem(position);
                DetailsActivity8.start(GridActivity8.this, imageUrl, view);
            }
        });
    }

    public static class ImagesAdapter extends ArrayAdapter<String>{
        public ImagesAdapter(Context context, String[] objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_image, parent, false);

            ImageView v = (ImageView) convertView;

            Picasso
                    .with(getContext())
                    .load(getItem(position))
                    .into(v);

            return v;
        }
    }
}
