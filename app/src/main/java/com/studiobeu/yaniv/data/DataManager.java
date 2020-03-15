package com.studiobeu.yaniv.data;

import android.content.Context;
import android.content.res.Resources;

import com.studiobeu.yaniv.application.di.ApplicationContext;
import com.studiobeu.yaniv.data.local.PreferenceService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {

    private final PreferenceService mPreferenceService;
    private final Context mContext;
    private final Resources mResource;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       PreferenceService prefService) {
        mContext = context;
        mPreferenceService = prefService;
        mResource = context.getResources();
    }


}
