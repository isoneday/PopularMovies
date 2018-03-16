package com.blackswan.popularmovies.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.blackswan.popularmovies.R;
import com.blackswan.popularmovies.activity.PopularMovieDetailActivity;
import com.blackswan.popularmovies.adapter.PopularMoviesAdapter;
import com.blackswan.popularmovies.helper.Constans;
import com.blackswan.popularmovies.helper.NetData;
import com.blackswan.popularmovies.model.PopularMoviesModel;

/**
 * Created by iswandi on 7/07/2017.
 */


public class PopularMoviesFragment extends Fragment {
    private ArrayAdapter<PopularMoviesModel> adapter;
    Fragment frag;
     public PopularMoviesFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {

        NetData syncDataMovies = new NetData(getActivity(), adapter);
        SharedPreferences preference= PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortOrder = preference.getString(getString(R.string.sort_key),
                getResources().getString(R.string.order_pop));
        syncDataMovies.execute(sortOrder);
        super.onStart();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_movies,container, false);

//        AlertDialog.Builder dialogalert = new AlertDialog.Builder(getActivity());
//        dialogalert.setView(view);
//        dialogalert.show();
        adapter = new PopularMoviesAdapter(getActivity(), R.layout.item_popularmovies);

        final GridView gridmovies = (GridView) view.findViewById(R.id.gridmovie);
        gridmovies.setAdapter(adapter);

        gridmovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                PopularMoviesModel movie = adapter.getItem(position);
                int id = movie.getId();
                Intent intent = new Intent(getActivity(), PopularMovieDetailActivity.class);
                intent.putExtra(Constans.ID, movie);
           //     Toast.makeText(getActivity(), "movie :" +movie, Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

        return view;

    }


}
