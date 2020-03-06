package com.studiobeu.yaniv.injection.component;

import com.studiobeu.yaniv.activity.EditPlayerActivity;
import com.studiobeu.yaniv.activity.GameActivity;
import com.studiobeu.yaniv.activity.MainActivity;
import com.studiobeu.yaniv.activity.PlayerSelect;
import com.studiobeu.yaniv.activity.YanivActivity;
import com.studiobeu.yaniv.injection.PerActivity;
import com.studiobeu.yaniv.injection.module.ActivityModule;

import dagger.Component;


/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(EditPlayerActivity editPlayerActivity);
    void inject(GameActivity gameActivity);
    void inject(MainActivity mainActivity);
    void inject(PlayerSelect playerSelect);
    void inject(YanivActivity yanivActivity);


}
