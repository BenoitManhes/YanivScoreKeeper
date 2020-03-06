package com.studiobeu.yaniv.application;

import android.app.Application;
import android.content.Context;

import com.studiobeu.yaniv.application.di.component.ApplicationComponent;
import com.studiobeu.yaniv.application.di.component.DaggerApplicationComponent;
import com.studiobeu.yaniv.application.di.module.ApplicationModule;


/**
 * YanivApp application
 */
public class YanivApp extends Application {

    private ApplicationComponent component;
    private static YanivApp singleton;

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;

        initDagger();

    }

    public static YanivApp getInstance() {
        return singleton;
    }


    /**
     * get the dagger component
     * @return the component
     */
    public ApplicationComponent getApplicationComponent() {
        return component;
    }


    public static YanivApp get(Context context) {
        return (YanivApp) context.getApplicationContext();
    }

    private void initDagger() {
        if (this.component == null) {
            this.component = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
    }

}
