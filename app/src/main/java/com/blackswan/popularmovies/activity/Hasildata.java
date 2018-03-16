package com.blackswan.popularmovies.activity;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.blackswan.popularmovies.R;
import com.blackswan.popularmovies.database.MoviesCursorAdapter;
import com.blackswan.popularmovies.database.MoviesProvider;

public class Hasildata extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor>{
    private CursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasildata);
        cursorAdapter = new MoviesCursorAdapter(this, null, 0);
        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//           MoviesProvider a = cursorAdapter.getItem(position);
//                Toast.makeText(Hasildata.this, "sds :"+a, Toast.LENGTH_SHORT).show();
////
//                Intent intent = new Intent(Hasildata.this, PopularMovieDetailActivity.class);
//                intent.putExtra(Constans.MOVIE_DATA, a);
//                startActivity(intent);

            }
        });
        restartLoader();
        getLoaderManager().initLoader(0, null, this);

    }
    private void restartLoader() {
        getLoaderManager().restartLoader(0,null,this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, MoviesProvider.CONTENT_URI,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }

    private void deleteAllContacts() {

        getContentResolver().delete(MoviesProvider.CONTENT_URI,null,null);
        restartLoader();
        Toast.makeText(this,getString(R.string.delete),Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item =menu.findItem(R.id.settings);
        MenuItem item1 =menu.findItem(R.id.mn_favorit);
        item.setVisible(false);
        item1.setVisible(false);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            return true;
        } else if(id==R.id.deleteAllContacts) {
            deleteAllContacts();
        }
        else {
            Intent intent = new Intent(this, Hasildata.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }


}
