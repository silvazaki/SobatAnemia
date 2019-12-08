package com.sobatanemia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.sobatanemia.util.SharedPrefHelper;

import static com.sobatanemia.STATIC_NAME.ID;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;

    SharedPrefHelper prefHelper;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);
        prefHelper = new SharedPrefHelper(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (prefHelper.getString(ID) != null) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                    finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
