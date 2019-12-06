package com.ashhasib.homefinder.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.ashhasib.homefinder.model.Token;
import com.ashhasib.homefinder.model.User;

public class UserSessionManager {

    private Context context;
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    public UserSessionManager(){}

    public UserSessionManager(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    private static String PREF_NAME = "AppPreference";
    private static String TOKEN_KEY = "TOKEN";
    private static String STATUS = "STATUS";

    private static String USERNAME = "USERNAME";
    private static String PASSWORD = "PASSWORD";

    public void write(Token token, User user) {


        editor.putString(TOKEN_KEY, token.getToken());
        editor.putBoolean(STATUS, true);
        editor.putString(USERNAME, user.getUsername());
        editor.putString(PASSWORD, user.getPassword());

        editor.commit();
    }


    public void clear() {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.clear();
        editor.commit();
    }


    public boolean isNotEmpty() {
        return preferences.getBoolean(STATUS, false);
    }


    public String getTokenKey() {
        return preferences.getString(TOKEN_KEY, "400");
    }

}
