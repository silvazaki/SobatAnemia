package com.sobatanemia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sobatanemia.service.PlaySoundService;
import com.sobatanemia.util.PreferenceUtil;

import static com.sobatanemia.STATIC_NAME.ALARM_TIME;


public class NotifActivity extends AppCompatActivity {

    Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_notif);

        startService(new Intent(this, PlaySoundService.class));

        stop = findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(NotifActivity.this, PlaySoundService.class));
                PreferenceUtil pref = new PreferenceUtil(NotifActivity.this);
                pref.delete(ALARM_TIME);
                startActivity(new Intent(getApplicationContext(), UploadActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
