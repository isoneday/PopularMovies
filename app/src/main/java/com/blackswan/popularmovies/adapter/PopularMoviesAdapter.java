package com.blackswan.popularmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackswan.popularmovies.R;
import com.blackswan.popularmovies.model.PopularMoviesModel;
import com.squareup.picasso.Picasso;

/**
 * Created by iswandi on 7/07/2017.
 */


public class PopularMoviesAdapter extends ArrayAdapter<PopularMoviesModel> {
    ImageView poster;
    TextView title;
    public PopularMoviesAdapter(Context context, int resources) {
        super(context, 0, resources);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        PopularMoviesModel moviesList = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_popularmovies, parent, false);
        }
        poster= (ImageView) view.findViewById(R.id.poster);
        title = (TextView) view.findViewById(R.id.title);
        Picasso.with(getContext()).load(moviesList.getImageurl()).error(R.drawable.noimage).placeholder(R.drawable.movie_place).fit().into(poster);
        title.setText(moviesList.getTitle());
        return view;
    }
}
