package com.sobatanemia;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sobatanemia.model.login.LoginResponse;
import com.sobatanemia.util.DialogHelper;
import com.sobatanemia.util.SharedPrefHelper;

import static com.sobatanemia.STATIC_NAME.ID;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog = DialogHelper.loading(this);
        edUsername = findViewById(R.id.ed_username);
        edPassword = findViewById(R.id.ed_password);
    }

    public void login(View view) {
        if (!TextUtils.isEmpty(edUsername.getText().toString()) &&
                !TextUtils.isEmpty(edPassword.getText().toString())) {
            dialog.show();
            new ApiClient().login(edUsername.getText().toString(), edPassword.getText().toString(), new LoginResponse.LoginResponseCallback() {
                @Override
                public void onSuccess(LoginResponse loginResponse) {
                    dialog.cancel();
                    Toast.makeText(LoginActivity.this, "sukses", Toast.LENGTH_SHORT).show();
                    SharedPrefHelper prefHelper = new SharedPrefHelper(getApplicationContext());
                    prefHelper.putString(ID, String.valueOf(loginResponse.data.id));
                    Intent startActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startActivityIntent);
                }

                @Override
                public void onError(String message) {
                    dialog.cancel();
                    Toast.makeText(LoginActivity.this, "gagal login", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Lengkapi form dulu", Toast.LENGTH_SHORT).show();
        }
    }
}
