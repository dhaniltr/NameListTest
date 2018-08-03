package com.perfomatix.namelistapp.view.listing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.perfomatix.namelistapp.R;
import com.perfomatix.namelistapp.utility.AppConstants;
import com.perfomatix.namelistapp.view.BaseActivity;

/**
 * This Activity is used to show the splash screen
 */
public class SplashScreenActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadHome();
            }
        }, AppConstants.SPLASH_TIME_OUT);
    }

    /**
     * Method to load NameListActivity
     */
    private void loadHome(){
        Intent intent = new Intent(this, NameListActivity.class);
        startActivity(intent);
        finish();
    }
}


