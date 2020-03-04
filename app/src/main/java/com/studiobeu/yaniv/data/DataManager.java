package com.studiobeu.yaniv.data;


import android.content.Context;

import com.studiobeu.yaniv.data.local.PreferenceHelper;
import com.studiobeu.yaniv.data.local.database.AppDataBase;

public class DataManager {

    private final AppDataBase mAppDataBase;
    private final PreferenceHelper mPreferenceHelper;
    private final Context mContext;

//    @Inject
    public DataManager( Context context, AppDataBase appDataBase, PreferenceHelper preferenceHelper){
        this.mContext = context;
        this.mAppDataBase = appDataBase;
        this.mPreferenceHelper = preferenceHelper;
    }
}
