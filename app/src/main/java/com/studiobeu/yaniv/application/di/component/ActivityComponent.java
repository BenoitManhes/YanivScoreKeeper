package com.studiobeu.yaniv.application.di.component;

import com.studiobeu.yaniv.ui.main.activity.EditPlayerActivity;
import com.studiobeu.yaniv.ui.main.activity.GameActivity;
import com.studiobeu.yaniv.ui.main.activity.MainActivity;
import com.studiobeu.yaniv.ui.main.activity.PlayerSelect;
import com.studiobeu.yaniv.ui.main.activity.YanivActivity;
import com.studiobeu.yaniv.application.di.PerActivity;
import com.studiobeu.yaniv.application.di.module.ActivityModule;
import com.studiobeu.yaniv.ui.main.home.HomeActivity;

import dagger.Component;


/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(HomeActivity homeActivity);
    void inject(EditPlayerActivity editPlayerActivity);
    void inject(GameActivity gameActivity);
    void inject(MainActivity mainActivity);
    void inject(PlayerSelect playerSelect);
    void inject(YanivActivity yanivActivity);


}
