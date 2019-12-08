package com.sobatanemia.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sobatanemia.NotifActivity;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = AlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startActivityIntent = new Intent(context, NotifActivity.class);
        startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startActivityIntent);
    }
}
