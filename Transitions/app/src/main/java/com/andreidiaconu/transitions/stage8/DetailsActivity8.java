package com.andreidiaconu.transitions.stage8;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.WindowCompat;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.andreidiaconu.transitions.R;
import com.squareup.picasso.Picasso;

/**
 * Created by andrei on 4/14/2015.
 */
public class DetailsActivity8 extends Activity {

    public static void start(Activity from, String imageUrl, View initialView){
        Intent intent = new Intent(from, DetailsActivity8.class);
        intent.putExtra("imageUrl", imageUrl);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Transition transition = new Explode();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            transition.excludeTarget(android.R.id.navigationBarBackground, true);
            from.getWindow().setExitTransition(transition);
        }

        Bundle animationBundle = ActivityOptionsCompat.makeSceneTransitionAnimation(from, initialView, "shared_image").toBundle();
        ActivityCompat.startActivity(from,intent, animationBundle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_shared);
        ((Toolbar) findViewById(R.id.action_bar)).setTitle("Details - Stage 8");

        ImageView imageView = (ImageView) findViewById(R.id.image);
        String imageUrl = getIntent().getStringExtra("imageUrl");

        Picasso
                .with(this)
                .load(imageUrl)
                .into(imageView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Transition transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            transition.excludeTarget(android.R.id.navigationBarBackground, true);
            getWindow().setEnterTransition(transition);
        }
    }

}
