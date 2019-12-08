package com.ashhasib.homefinder.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import com.ashhasib.homefinder.model.Token;
import com.ashhasib.homefinder.model.User;

public class UserSessionManager {

    private static String PREF_NAME = "AppPreference";
    private static String TOKEN_KEY = "TOKEN";
    private static String STATUS = "STATUS";

    private static String USERNAME = "USERNAME";
    private static String PASSWORD = "PASSWORD";


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    //public UserSessionManager(){}



    public UserSessionManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }


    public void write(Token token, User user) {

        editor.putString(TOKEN_KEY, token.getToken());
        editor.putString(USERNAME, user.getUsername());
        editor.putString(PASSWORD, user.getPassword());
        editor.putBoolean(STATUS, true);
        editor.commit();
    }

    public void clear() {

        editor.clear();
        editor.commit();
    }

    public boolean isNotEmpty () {
        return sharedPreferences.getBoolean(STATUS, false);
    }

    public String getTokenKey () {
        return sharedPreferences.getString(TOKEN_KEY, "400");
    }
}
