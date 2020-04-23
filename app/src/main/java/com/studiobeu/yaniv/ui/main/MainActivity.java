package com.studiobeu.yaniv.ui.main;

import android.os.Bundle;

import com.studiobeu.yaniv.R;
import com.studiobeu.yaniv.ui.base.BaseActivity;
import com.studiobeu.yaniv.ui.main.home.HomeFragment;
import com.studiobeu.yaniv.ui.main.playerSelection.PlayerSelectionFragment;
import com.studiobeu.yaniv.utils.ActivityUtils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends BaseActivity {

    private Fragment mHomeFragment, mPlayerSelectionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setHomeFragment();
    }

    public void setHomeFragment(){
        if(mHomeFragment == null)
            mHomeFragment = HomeFragment.newInstance();

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mHomeFragment,R.id.content_frame);
    }

    public void setPlayerSelectionFragment(){
        if(mPlayerSelectionFragment == null)
            mPlayerSelectionFragment = PlayerSelectionFragment.newInstance();

        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mPlayerSelectionFragment,R.id.content_frame);
    }
}
