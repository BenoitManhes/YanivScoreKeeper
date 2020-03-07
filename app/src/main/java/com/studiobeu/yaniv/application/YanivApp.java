package com.studiobeu.yaniv.application;

import android.app.Application;
import android.content.Context;

import com.studiobeu.yaniv.application.di.component.ApplicationComponent;
import com.studiobeu.yaniv.application.di.component.DaggerApplicationComponent;
import com.studiobeu.yaniv.application.di.module.ApplicationModule;
import com.studiobeu.yaniv.application.di.module.RoomModule;


/**
 * YanivApp application
 */
public class YanivApp extends Application{

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();

    }

    public YanivApp(){
        super();
    }

    /**
     * getApp the dagger component
     * @return the component
     */
    public ApplicationComponent getApplicationComponent() {
        return component;
    }


    public static YanivApp getApp(Context context) {
        return (YanivApp) context.getApplicationContext();
    }

    private void initDagger() {
        if (this.component == null) {
            this.component = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .roomModule(new RoomModule(this))
                    .build();
            this.component.inject(this);
        }
    }

}
