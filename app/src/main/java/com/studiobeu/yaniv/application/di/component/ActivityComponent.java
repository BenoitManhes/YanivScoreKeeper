package com.studiobeu.yaniv.application.di.component;

import com.studiobeu.yaniv.ui.main.MainActivity;
import com.studiobeu.yaniv.application.di.PerActivity;
import com.studiobeu.yaniv.application.di.module.ActivityModule;
import com.studiobeu.yaniv.ui.activity.*;
import dagger.Component;


/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(EditPlayerActivity editPlayerActivity);
    void inject(GameActivity gameActivity);
    void inject(PlayerSelect playerSelect);
    void inject(YanivActivity yanivActivity);


}
