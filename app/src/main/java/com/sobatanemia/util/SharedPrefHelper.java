package com.sobatanemia.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {

    private final String MLIB_PREF = "tikrar";

    private SharedPreferences mPreferences;
    SharedPreferences.Editor editor;

    public SharedPrefHelper(Context context) {
        mPreferences = context.getSharedPreferences(MLIB_PREF, Context.MODE_PRIVATE);
        editor = mPreferences.edit();
    }

    public String getString(String key){
        return mPreferences.getString(key,null);
    }

    public int getInt(String key){
        return mPreferences.getInt(key,0);
    }

    public void putString(String key, String value){
        mPreferences.edit().putString(key,value).apply();
    }

    public void putInt(String key, int value){
        mPreferences.edit().putInt(key,value).apply();
    }

    public void clear(){
        mPreferences.edit().clear().apply();
    }


}
