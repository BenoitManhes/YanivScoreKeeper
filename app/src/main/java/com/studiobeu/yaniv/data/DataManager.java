package com.studiobeu.yaniv.data;


import android.content.Context;

import com.studiobeu.yaniv.data.local.PreferenceService;
import com.studiobeu.yaniv.data.local.database.AppDataBase;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {

    private final AppDataBase mAppDataBase;
    private final PreferenceService mPreferenceService;
    private final Context mContext;

    @Inject
    public DataManager( Context context, AppDataBase appDataBase, PreferenceService preferenceService){
        this.mContext = context;
        this.mAppDataBase = appDataBase;
        this.mPreferenceService = preferenceService;
    }

    public PreferenceService getPreferenceHelper() {
        return mPreferenceService;
    }
}
