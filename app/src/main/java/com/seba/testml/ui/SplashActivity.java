package com.seba.testml.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;

import com.seba.testml.R;

public class SplashActivity extends BaseActivity {

    private static final long SPLASH_TIME = 5000;
    private Handler handler;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent searchIntent = new Intent(SplashActivity.this, SearchActivity.class);
            startActivity(searchIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(false);
//            actionBar.setDisplayShowHomeEnabled(false);
//            actionBar.setDisplayShowTitleEnabled(false);
//        }

        handler = new Handler();
    }

    @Override
    public void onResume() {
        handler.postDelayed(runnable, SPLASH_TIME);
        super.onResume();
    }

}