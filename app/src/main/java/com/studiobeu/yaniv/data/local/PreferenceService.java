package com.studiobeu.yaniv.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.studiobeu.yaniv.application.di.ApplicationContext;

import javax.inject.Singleton;

@Singleton
public class PreferenceService {

    private static final String PREF_FILE_NAME = "yaniv_pref_file";

    private final SharedPreferences preferences;

    public PreferenceService(@ApplicationContext Context context) {
        this.preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }
}
