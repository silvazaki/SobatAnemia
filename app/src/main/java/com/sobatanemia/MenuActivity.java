package com.sobatanemia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void login(View view){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    public void daftar(View view){
        startActivity(new Intent(getApplicationContext(), DaftarActivity.class));
    }

    public void keluar(View view){
        finish();
    }
}
