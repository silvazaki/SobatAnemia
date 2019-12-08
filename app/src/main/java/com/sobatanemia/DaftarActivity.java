package com.sobatanemia;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sobatanemia.model.register.RegisterResponse;
import com.sobatanemia.util.DialogHelper;
import com.sobatanemia.util.SharedPrefHelper;

import static com.sobatanemia.STATIC_NAME.ID;

public class DaftarActivity extends AppCompatActivity {

    EditText edUsername, edPassword, edNama;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        dialog = DialogHelper.loading(this);
        edUsername = findViewById(R.id.ed_username);
        edPassword = findViewById(R.id.ed_password);
        edNama = findViewById(R.id.ed_nama);
    }

    public void daftar(View view) {
        if (!TextUtils.isEmpty(edUsername.getText().toString()) &&
                !TextUtils.isEmpty(edPassword.getText().toString()) &&
                !TextUtils.isEmpty(edPassword.getText().toString())) {
            dialog.show();
            new ApiClient().daftar(edUsername.getText().toString(),
                    edPassword.getText().toString(), edNama.getText().toString(),
                    new RegisterResponse.RegisterResponeCallback() {
                        @Override
                        public void onSuccess(RegisterResponse registerResponse) {
                            dialog.cancel();
                            Toast.makeText(DaftarActivity.this, "sukses", Toast.LENGTH_SHORT).show();
                            SharedPrefHelper prefHelper = new SharedPrefHelper(getApplicationContext());
                            prefHelper.putString(ID, String.valueOf(registerResponse.user.id));
                            Intent startActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(startActivityIntent);
                        }

                        @Override
                        public void onError(String message) {
                            dialog.cancel();
                            Toast.makeText(DaftarActivity.this, "gagal login", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Lengkapi form dulu", Toast.LENGTH_SHORT).show();
        }
    }
}
