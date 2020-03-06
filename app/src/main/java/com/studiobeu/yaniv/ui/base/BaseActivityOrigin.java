package com.studiobeu.yaniv.ui.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.appcompat.app.AppCompatActivity;

import com.studiobeu.yaniv.application.YanivApp;
import com.studiobeu.yaniv.application.di.component.ActivityComponent;
import com.studiobeu.yaniv.application.di.component.DaggerActivityComponent;
import com.studiobeu.yaniv.application.di.module.ActivityModule;


public class BaseActivityOrigin extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private ActivityComponent mActivityComponent;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(YanivApp.get(this).getApplicationComponent())
                    .build();
        }
        return mActivityComponent;
    }
}
