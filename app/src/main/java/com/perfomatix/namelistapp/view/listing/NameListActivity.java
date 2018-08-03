package com.perfomatix.namelistapp.view.listing;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.perfomatix.namelistapp.R;
import com.perfomatix.namelistapp.view.BaseActivity;

/**
 * This is the mainactivity of this app
 */
public class NameListActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_names_list);
    }
}
