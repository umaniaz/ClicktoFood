package com.food.clicktofood.SessionData;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.food.clicktofood.Model.LoginResponse;
import com.google.gson.Gson;

public class SessionData {
    private final String TAG = "xapp_" + this.getClass().getSimpleName();
    Context context;
    public static final String ID = "id";
    private static final String USERDATAMODEL = "user_data_model";
    private static final String APPSTATE = "app_state";
    private static final String REMEMBERME = "remember_me";
    private static final String PASSWORD = "password";

    private final String BASIC_PREFS = "xlimited";
    private final String BASIC_PREFS_APP_STATE = "appState";
    private final String BASIC_PREFS_REMEMBER_ME = "rememberMe";
    private final String BASIC_PREFS_PASSWORD = "password";

    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencesAppState;
    private SharedPreferences sharedPreferencesRememberMe;
    private SharedPreferences sharedPreferencesPassword;

    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor editorAppState;
    private SharedPreferences.Editor editorRememberMe;
    private SharedPreferences.Editor editorPassword;

    private Gson gson;

    public SessionData(Context context) {
        this.context = context;
        gson = new Gson();
        sharedPreferences = this.context.getSharedPreferences(BASIC_PREFS, Context.MODE_PRIVATE);
        sharedPreferencesAppState = this.context.getSharedPreferences(BASIC_PREFS_APP_STATE, Context.MODE_PRIVATE);
        sharedPreferencesRememberMe = this.context.getSharedPreferences(BASIC_PREFS_REMEMBER_ME, Context.MODE_PRIVATE);
        sharedPreferencesPassword = this.context.getSharedPreferences(BASIC_PREFS_PASSWORD, Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();
        editorAppState = sharedPreferencesAppState.edit();
        editorRememberMe = sharedPreferencesRememberMe.edit();
        editorPassword = sharedPreferencesPassword.edit();
    }

    public LoginResponse getUserDataModel() {
        String str = sharedPreferences.getString(USERDATAMODEL, null);
        LoginResponse model = null;
        if (str != null) {
            try {
                model = gson.fromJson(str, LoginResponse.class);
            } catch (Exception e) {
                Log.d(TAG, "exception " + e.toString());
                model = null;
            }
        }
        return model;
    }

    public void setUserDataModel(LoginResponse model) {
        String str = gson.toJson(model);
        editor.putString(USERDATAMODEL, str);
        editor.commit();
    }

    public void setAppState(Boolean value){
        editorAppState.putBoolean(APPSTATE, value);
        editorAppState.commit();
    }

    public boolean getAppState(){
        return sharedPreferencesAppState.getBoolean(APPSTATE, false);
    }

    public void setRememberMe(Boolean value){
        editorRememberMe.putBoolean(REMEMBERME, value);
        editorRememberMe.commit();
    }

    public boolean getRememberMe(){
        return sharedPreferencesRememberMe.getBoolean(REMEMBERME, false);
    }

    public void setPassword(String value){
        editorPassword.putString(PASSWORD, value);
        editorPassword.commit();
    }

    public String getPassword(){
        return sharedPreferencesPassword.getString(PASSWORD, null);
    }



    public void clearPrefData() {
        editor.clear();
        editor.commit();
        editorRememberMe.clear();
        editorRememberMe.commit();
        editorPassword.commit();
        editorPassword.commit();
    }
}
