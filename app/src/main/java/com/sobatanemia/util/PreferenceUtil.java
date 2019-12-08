package com.sobatanemia.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {
    private static final String TAG = PreferenceUtil.class.getSimpleName();

    // 設定ファイル名
    private static final String CONFIG_FILE_NAME = "alarm_settings";

    private SharedPreferences mPreferences;
    SharedPreferences.Editor editor;

    public PreferenceUtil(Context context) {
        mPreferences = context.getSharedPreferences(CONFIG_FILE_NAME, Context.MODE_PRIVATE);
        editor = mPreferences.edit();
    }

    public String getString(String key) {
        return mPreferences.getString(key, null);
    }

    public int getInt(String key) {
        return mPreferences.getInt(key, 0);
    }

    public long getLong(String key) {
        return mPreferences.getLong(key, (long) 0.0);
    }

    public void putLong(String key, long value) {
        mPreferences.edit().putLong(key, value).apply();
    }

    public void putString(String key, String value) {
        mPreferences.edit().putString(key, value).apply();
    }

    public void putInt(String key, int value) {
        mPreferences.edit().putInt(key, value).apply();
    }

    public void delete(String key){
        mPreferences.edit().remove(key).apply();
    }

    public void clear() {
        mPreferences.edit().clear().apply();
    }

}