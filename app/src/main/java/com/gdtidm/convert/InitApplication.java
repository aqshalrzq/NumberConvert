package com.gdtidm.convert;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class InitApplication extends Application {

    public static final String NIGHT_MODE           =   "NIGHT_MODE";
    private boolean isNightModeEnabled              =   false;
    private static InitApplication singleton        =   null;

    public static InitApplication getInstance() {
        if (singleton == null) {
            singleton = new InitApplication();
        }
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton                   =   this;
        SharedPreferences mPrefs    =   PreferenceManager.getDefaultSharedPreferences(InitApplication.this);
        this.isNightModeEnabled     =   mPrefs.getBoolean(NIGHT_MODE, false);
    }

    public boolean isNightModeEnabled() {
        return isNightModeEnabled;
    }

    public void setIsNightModeEnabled(boolean isNightModeEnabled) {
        this.isNightModeEnabled = isNightModeEnabled;

        SharedPreferences mPrefs        =   PreferenceManager.getDefaultSharedPreferences(InitApplication.this);
        SharedPreferences.Editor editor =   mPrefs.edit();
        editor.putBoolean(NIGHT_MODE, isNightModeEnabled);
        editor.apply();
    }
}
