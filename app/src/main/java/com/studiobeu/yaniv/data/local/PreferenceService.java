package com.studiobeu.yaniv.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.studiobeu.yaniv.application.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferenceService {

    private static final String PREF_FILE_NAME = "yaniv_pref_file";

    private final SharedPreferences mPref;

    @Inject
    public PreferenceService(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }
}
