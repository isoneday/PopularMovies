package com.blackswan.popularmovies;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.blackswan.popularmovies.activity.MainActivity;
/**
 * Created by iswandi on 7/07/2017.
 */

public class SplashActivity extends AppCompatActivity {


TextView txt1;
    Typeface facenormal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //untuk melakukan jeda/penundaan aktivity selanjutnya
       txt1 =(TextView)findViewById(R.id.textView1);
        facenormal = Typeface.createFromAsset(getAssets(), "fonts/zenone.ttf");
        txt1.setTypeface(facenormal);
        final Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
startActivity(new Intent(SplashActivity.this,MainActivity.class));

            }
        },3000);

    }


    @Override
    public void onBackPressed() {
        //return;
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("konfirmasi");
        builder1.setMessage("apakah anda yakin keluar");
        builder1.setPositiveButton("iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                System.exit(0);
            }
        });
        builder1.setNegativeButton("tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder1.show();
    }
}
