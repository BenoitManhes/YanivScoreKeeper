package com.studiobeu.yaniv.ui.main.home;

import android.os.Bundle;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.ui.base.BaseActivity;
import com.studiobeu.yaniv.utils.ActivityUtils;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (homeFragment == null){
            homeFragment = HomeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), homeFragment,R.id.content_frame);
        }
    }
}
