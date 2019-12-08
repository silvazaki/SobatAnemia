package com.sobatanemia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.sobatanemia.util.SharedPrefHelper;

import static com.sobatanemia.STATIC_NAME.WEEKS;

public class MisiActivity extends AppCompatActivity {

    ImageView ivBerhasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_misi);
        ivBerhasil = findViewById(R.id.iv_berhasil);

        ivBerhasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MateriActivity.class));
            }
        });
    }
}
