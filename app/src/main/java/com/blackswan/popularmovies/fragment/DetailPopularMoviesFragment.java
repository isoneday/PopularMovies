package com.blackswan.popularmovies.fragment;


import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blackswan.popularmovies.R;
import com.blackswan.popularmovies.activity.Hasildata;
import com.blackswan.popularmovies.adapter.VideoListAdapter2;
import com.blackswan.popularmovies.database.MoviesProvider;
import com.blackswan.popularmovies.database.DBOpenHelper;
import com.blackswan.popularmovies.helper.Constans;
import com.blackswan.popularmovies.model.PopularMoviesModel;
import com.blackswan.popularmovies.model.Result;
import com.blackswan.popularmovies.model.ReviewModel;
import com.blackswan.popularmovies.model.VideoModel;
import com.blackswan.popularmovies.rest.RestAPI;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by iswandi on 7/07/2017.
 */

public class DetailPopularMoviesFragment extends Fragment  {

    public DetailPopularMoviesFragment() {
        setHasOptionsMenu(true);
    }

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout CoordinatorLayout;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout CollapsingToolBar;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.poster)
    ImageView poster;
    @BindView(R.id.backposter)
    ImageView backposter;
    @BindView(R.id.release_date)
    TextView releasedate;
    @BindView(R.id.rating)
    TextView rating;
    @BindView(R.id.synopsis)
    TextView synopsis;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    RecyclerView trailervideo,reviewvideo;
    RecyclerView.LayoutManager layoutManager,layoutManager2;
    List<Result> videolist;
    private CursorAdapter cursorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tes_detail, container, false);
        final PopularMoviesModel movie = getActivity().getIntent().getParcelableExtra(Constans.ID);
        trailervideo = (RecyclerView)rootView.findViewById(R.id.trailervideo);
        reviewvideo = (RecyclerView)rootView.findViewById(R.id.reviewvideo);
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) rootView.findViewById(R.id.collapsing_toolbar);
        //collapsingToolbarLayout.setTitle(movie.getTitle());
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       insertContact(movie.getTitle(), movie.getImageurl());
                                       startActivity(new Intent(getActivity(),Hasildata.class));

                              //         getActivity().startActivity(new Intent(getActivity(),MainActivityContentProvider.class));
                                   }
                               });
                ButterKnife.bind(this, rootView);
        layoutManager =new LinearLayoutManager(getActivity());
        trailervideo.setLayoutManager(layoutManager);
        layoutManager2 =new LinearLayoutManager(getActivity());
        reviewvideo.setLayoutManager(layoutManager2);

        getdatavideo(movie);
        getdataremovie(movie);
        title.setText(movie.getTitle());
        Picasso.with(getContext())
                .load(movie.getImageurl())
                .placeholder(R.drawable.movie_place)
                .error(R.drawable.noimage)
                .into(poster);
        Picasso.with(getContext())
                .load((movie.getBackPoster()))
                .placeholder(R.drawable.movie_place)
                .error(R.drawable.noimage)
                .into(backposter);
        releasedate.setText("Released :\n" + movie.getRelease_date());
        rating.setText(String.valueOf("Rating :\n" + movie.getRating() + "/10"));
        synopsis.setText("Synopsis :\n" + movie.getSynopsis());
        return rootView;
    }


    private void insertContact(String contactName,String contactPhone) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.MOVIE_NAME,contactName);
        values.put(DBOpenHelper.MOVIE_URL,contactPhone);
        Uri contactUri  = getActivity().getContentResolver().insert(MoviesProvider.CONTENT_URI,values);
        Toast.makeText(getActivity(),"Created Contact " + contactName,Toast.LENGTH_LONG).show();
    }


    private void getdataremovie(PopularMoviesModel movie) {
        Retrofit r = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL_VIDEO)
                //gsonconverter untuk parsing json dari webservice menjadi java object
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        String id = String.valueOf(movie.getId());

        //String id = "119450";
        RestAPI api = r.create(RestAPI.class);
        Call<ReviewModel> modelreview =api.request_review(id,Constans.URL_API_KEY,Constans.BAHASA,Constans.PAGE);
        modelreview.enqueue(new Callback<ReviewModel>() {
            @Override
            public void onResponse(Call<ReviewModel> call, Response<ReviewModel> response) {
                videolist =response.body().getResults();
                //method untuk menampilkan data json object
                //String yang digunakan untuk menympan semua field yang ada pada json object
                String items[] = new String[videolist.size()];
                for (int i =0;i<videolist.size();i++){
                    items[i]=videolist.get(i).getAuthor();
                }
                //adapter untuk mengisi data di recyclerview
                ReviewAdapter adapter = new ReviewAdapter(DetailPopularMoviesFragment.this,videolist);
                reviewvideo.setAdapter(adapter);
//                VideoListAdapter  adapter = new VideoListAdapter(frag,reviewlist);
//                holder.trailervideo.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<ReviewModel> call, Throwable t) {

            }
        });

    }

    private void getdatavideo(PopularMoviesModel movie) {


        Retrofit r = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL_VIDEO)
                //gsonconverter untuk parsing json dari webservice menjadi java object
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        String id = String.valueOf(movie.getId());

        //String id = "119450";
        RestAPI api = r.create(RestAPI.class);
        Call<VideoModel> modelVideoCall =api.request_videos(id,Constans.URL_API_KEY,Constans.BAHASA);
        modelVideoCall.enqueue(new Callback<VideoModel>() {
            @Override
            public void onResponse(Call<VideoModel> call, Response<VideoModel> response) {
                videolist =response.body().getResults();
                //method untuk menampilkan data json object
                //String yang digunakan untuk menympan semua field yang ada pada json object
                String items[] = new String[videolist.size()];
                for (int i =0;i<videolist.size();i++){
                    items[i]=videolist.get(i).getName();
                }
                //adapter untuk mengisi data di recyclerview
                VideoListAdapter2 adapter = new VideoListAdapter2(DetailPopularMoviesFragment.this,videolist);
                trailervideo.setAdapter(adapter);
//                VideoListAdapter  adapter = new VideoListAdapter(frag,reviewlist);
//                holder.trailervideo.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<VideoModel> call, Throwable t) {

            }
        });






    }


}

