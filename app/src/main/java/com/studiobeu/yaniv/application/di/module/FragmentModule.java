package com.studiobeu.yaniv.injection.module;

import android.app.Activity;
import android.content.Context;

import com.studiobeu.yaniv.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

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
