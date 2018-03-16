package com.blackswan.popularmovies.database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackswan.popularmovies.R;
import com.squareup.picasso.Picasso;

public class MoviesCursorAdapter extends CursorAdapter {
    String movieid;
   public  int dataid;

    public MoviesCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(
                R.layout.movie_list_item,viewGroup,false );
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        movieid = cursor.getString(
                cursor.getColumnIndex(DBOpenHelper.MOVIE_ID));
       dataid =Integer.parseInt(movieid);
        final String moviename = cursor.getString(
                cursor.getColumnIndex(DBOpenHelper.MOVIE_NAME));
        String movieurl = cursor.getString(
                cursor.getColumnIndex(DBOpenHelper.MOVIE_URL));
        final TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        TextView urlTextView = (TextView) view.findViewById(R.id.urlTextView);
        ImageView imgfav = (ImageView)view.findViewById(R.id.imageDocIcon);
        Picasso.with(context).load(movieurl).error(R.drawable.noimage).placeholder(R.drawable.movie_place).fit().into(imgfav);

        nameTextView.setText(moviename);
        urlTextView.setText(movieurl);

        imgfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//      Object a = getItemId();
//                Toast.makeText(context, "sds :"+movieid, Toast.LENGTH_SHORT).show();
//
//   PopularMoviesModel movie = movieid;
//                Intent intent = new Intent(context, PopularMovieDetailActivity.class);
//                intent.putExtra(Constans.MOVIE_DATA, movieid);
//                context.startActivity(intent);

            }
        });
    }
}
