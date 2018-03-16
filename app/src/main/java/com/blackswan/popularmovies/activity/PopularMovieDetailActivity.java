package com.blackswan.popularmovies.activity;

import android.app.Activity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.blackswan.popularmovies.R;
import com.blackswan.popularmovies.fragment.DetailPopularMoviesFragment;

/**
 * Created by iswandi on 7/07/2017.
 */


public class PopularMovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detailmovies, new DetailPopularMoviesFragment())
                    .commit();
        }

    }


}
