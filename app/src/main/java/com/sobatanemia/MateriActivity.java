package com.sobatanemia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.sobatanemia.util.SharedPrefHelper;

import static com.sobatanemia.STATIC_NAME.WEEKS;

public class MateriActivity extends AppCompatActivity {

    ImageView pertanyaan1, pertanyaan2, pertanyaan3, pertanyaan4;
    ImageView jawaban1, jawaban2, jawaban3, jawaban4;

    SharedPrefHelper prefHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefHelper = new SharedPrefHelper(getApplicationContext());
        int weeks = prefHelper.getInt(WEEKS);
        final int nextWeeks = weeks;
        if (weeks == 1) {
            setContentView(R.layout.activity_materi1);
            pertanyaan1 = findViewById(R.id.iv_pertanyaan1);
            jawaban1 = findViewById(R.id.iv_jawaban1);
            pertanyaan1.setVisibility(View.GONE);
            jawaban1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prefHelper.putInt(WEEKS, (nextWeeks + 1));
                    Intent startActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startActivityIntent);
                }
            });
        } else if (weeks == 2) {
            setContentView(R.layout.activity_materi2);
            pertanyaan2 = findViewById(R.id.iv_pertanyaan2);
            jawaban2 = findViewById(R.id.iv_jawaban2);
            pertanyaan2.setVisibility(View.GONE);
            jawaban2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prefHelper.putInt(WEEKS, (nextWeeks + 1));
                    Intent startActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startActivityIntent);
                }
            });

        }  else if (weeks == 3) {
            setContentView(R.layout.activity_materi3);
            pertanyaan3 = findViewById(R.id.iv_pertanyaan3);
            jawaban3 = findViewById(R.id.iv_jawaban3);
            pertanyaan3.setVisibility(View.GONE);
            jawaban3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prefHelper.putInt(WEEKS, (nextWeeks + 1));
                    Intent startActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startActivityIntent);
                }
            });

        }else {
            setContentView(R.layout.activity_materi4);
            pertanyaan4 = findViewById(R.id.iv_pertanyaan4);
            jawaban4 = findViewById(R.id.iv_jawaban4);
            pertanyaan4.setVisibility(View.GONE);
            jawaban4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    prefHelper.putInt(WEEKS, (nextWeeks + 1));
                    Intent startActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK );
                    startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startActivityIntent);
                }
            });

        }
    }
}
