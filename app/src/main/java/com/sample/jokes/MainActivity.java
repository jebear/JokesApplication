package com.sample.jokes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    /**
     * Views
     * */
    private Button btnRandom;
    private Button btnName;
    private Button btnCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        if(isTablet()){
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.error))
                    .setMessage(getString(R.string.error_tablet))
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!isNetworkConnected()){
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.error))
                    .setMessage(getString(R.string.error_no_internet))
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    private boolean isTablet() {
        return (this.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }

    private void initViews(){
        btnRandom = (Button) findViewById(R.id.btnRandom);
        btnName = (Button) findViewById(R.id.btnName);
        btnCategory = (Button) findViewById(R.id.btnCategory);

        btnRandom.setOnClickListener(onclick);
        btnName.setOnClickListener(onclick);
        btnCategory.setOnClickListener(onclick);
    }

    private View.OnClickListener onclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent = null;

            switch (v.getId()){
                case R.id.btnRandom:
                    intent = new Intent(getApplicationContext(),JokeActivity.class);
                    intent.putExtra("url","http://api.icndb.com/jokes/random");
                    break;
                case R.id.btnName:
                    intent = new Intent(getApplicationContext(),NameDialogActivity.class);
                    break;
                case R.id.btnCategory:
                    intent = new Intent(getApplicationContext(),CategoryDialogActivity.class);
                    break;
            }
                if(intent!= null){}
                    startActivity(intent);
            }
    };

}
