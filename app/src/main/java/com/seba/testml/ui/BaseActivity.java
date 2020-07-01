package com.seba.testml.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.seba.testml.R;

public class BaseActivity extends AppCompatActivity {

    private String classSimpleName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        classSimpleName = getClass().getSimpleName();
    }

    protected void openActivity(Class c) {
        if (!classSimpleName.equals(c.getSimpleName())) {
            Intent intent = new Intent(getApplicationContext(), c);
            startActivity(intent);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anim_open_1, R.anim.anim_open_2);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_close_1, R.anim.anim_close_2);
    }

}