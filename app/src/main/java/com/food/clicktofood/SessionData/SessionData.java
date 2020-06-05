package com.food.clicktofood.SessionData;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.food.clicktofood.Model.LoginResponse;
import com.google.gson.Gson;

public class SessionData {
    public static final String ID = "id";
    private static final String USERDATAMODEL = "user_data_model";
    private static final String APPSTATE = "app_state";
    private final String TAG = "xapp_" + this.getClass().getSimpleName();
    Context context;
    private final String BASIC_PREFS = "xlimited";
    private final String BASIC_PREFS_APP_STATE = "appState";

    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencesAppState;

    private SharedPreferences.Editor editor;
    private SharedPreferences.Editor editorAppState;
    private Gson gson;

    public SessionData(Context context) {
        this.context = context;
        gson = new Gson();
        sharedPreferences = this.context.getSharedPreferences(BASIC_PREFS, Context.MODE_PRIVATE);
        sharedPreferencesAppState = this.context.getSharedPreferences(BASIC_PREFS_APP_STATE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editorAppState = sharedPreferencesAppState.edit();
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

    public void clearPrefData() {
        editor.clear();
        editor.commit();
    }
}
