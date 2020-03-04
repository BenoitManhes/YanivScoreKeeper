package com.studiobeu.yaniv.ui;

import android.app.Application;
import android.content.Context;

import com.studiobeu.yaniv.injection.component.ApplicationComponent;
import com.studiobeu.yaniv.injection.module.ApplicationModule;


/**
 * Global application
 */
public class Global extends Application {

    private ApplicationComponent mApplicationComponent;
    private static Global singleton;


    public static Context c;

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;

        c = getApplicationContext();

    }

    public static Global getInstance() {
        return singleton;
    }


    public Global() {
        super();
    }


    public static Global get(Context context) {
        return (Global) context.getApplicationContext();
    }

    public ApplicationComponent getApplicationComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

}
