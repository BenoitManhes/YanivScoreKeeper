package com.studiobeu.yaniv.application.di.module;

import android.app.Activity;
import android.content.Context;

import com.studiobeu.yaniv.application.di.ActivityContext;
import com.studiobeu.yaniv.ui.main.home.HomeContract;
import com.studiobeu.yaniv.ui.main.home.HomePresenter;
import com.studiobeu.yaniv.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class FragmentModule {

    private Activity mActivity;

    public FragmentModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

}
