package com.seba.testml.ui;

import android.os.Bundle;

import com.seba.testml.R;

public class SearchActivity extends BaseActivity {

    private static final int SPLASH_TIME = 2500; // ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        keepSplash();
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    private void keepSplash() {
        try {
            Thread.sleep(SPLASH_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}