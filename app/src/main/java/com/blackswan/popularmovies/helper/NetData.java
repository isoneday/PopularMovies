package com.blackswan.popularmovies.helper;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import com.blackswan.popularmovies.model.PopularMoviesModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by iswandi on 7/07/2017.
 */

public class NetData extends AsyncTask<String, Void, List<PopularMoviesModel>> {
    private ArrayAdapter<PopularMoviesModel> mAdapter;
    public static Context con;
    String poster, backposter, title, release_date, synopsis, rating,key;


    public NetData(Context context, ArrayAdapter<PopularMoviesModel> MovieAdapter) {
        con = context;
        mAdapter = MovieAdapter;
    }

    public List<PopularMoviesModel> getMoviesDataFromJson(String moviesJsonStr)
            throws JSONException {
        List<PopularMoviesModel> moviesdata = new ArrayList<>();
        JSONObject movieJson = new JSONObject(moviesJsonStr);
        JSONArray movieArray = movieJson.getJSONArray(Constans.OWN_RESULT);
        String imageURl;
        String posterURl;
        for (int i = 0; i < movieArray.length(); i++) {
            JSONObject movie = movieArray.getJSONObject(i);
            poster = movie.getString(Constans.POSTER);
            backposter = movie.getString(Constans.BACK_POSTER);
            title = movie.getString(Constans.TITLE);
            release_date = movie.getString(Constans.RELEASE_DATE);
            synopsis = movie.getString(Constans.SYNOPSIS);
            rating = movie.getString(Constans.VOTE);
         //   key = movie.getString(Constans.KEY_VIDEO);

            int id = movie.getInt(Constans.ID);
            imageURl = Constans.ROOT_POSTER_IMAGE_URL + poster;
            posterURl = Constans.ROOT_BACKDROP_IMAGE_URL + backposter;
            PopularMoviesModel movieList = new PopularMoviesModel(title, imageURl, release_date,synopsis, rating, posterURl, id);
            moviesdata.add(movieList);
        }
        return moviesdata;
    }

    @Override
    protected List<PopularMoviesModel> doInBackground(String... params) {
        String sortQuery = params[0];
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String movieJsonStr = null;
        try {
            switch (sortQuery) {
                case "0":
                    sortQuery = Constans.POPULAR;
                    break;
                case "1":
                    sortQuery = Constans.TOP_RATED;
                    break;
            }

            Uri builtUri = Uri.parse(Constans.BASE_URL)
                    .buildUpon()
                    .appendPath(sortQuery)
                    .appendQueryParameter(Constans.API, Constans.URL_API_KEY)
                    .appendQueryParameter(Constans.LANGUAGE, "en-US")
                    .build();

            URL url = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            movieJsonStr = buffer.toString();
        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }
        try {
            return getMoviesDataFromJson(movieJsonStr);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<PopularMoviesModel> movies) {
        if (movies != null) {
            mAdapter.clear();
            for (PopularMoviesModel movieForecastStr : movies) {
                mAdapter.add(movieForecastStr);
            }
        }
    }
}