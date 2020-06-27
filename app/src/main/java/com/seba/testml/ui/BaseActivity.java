package com.seba.testml.ui;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.seba.testml.R;

public class BaseActivity extends AppCompatActivity {

    protected void startAnimatedActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }



}
