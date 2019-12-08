package com.sobatanemia;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.sobatanemia.model.upload.UploadResponse;
import com.sobatanemia.util.DialogHelper;
import com.sobatanemia.util.FilePath;
import com.sobatanemia.util.SharedPrefHelper;

import java.io.File;

import static com.sobatanemia.STATIC_NAME.ID;

public class UploadActivity extends AppCompatActivity {

    TextView textViewFile;
    Button btnChoose, btnUpload;
    private String selectedFilePath;
    File selectedFile;
    private static final int PICK_FILE_REQUEST = 1;
    private String TAG = "hasil";
    SharedPrefHelper prefHelper;

    Dialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        prefHelper = new SharedPrefHelper(this);

        loading = DialogHelper.loading(this);

        textViewFile = findViewById(R.id.tv_file);
        btnChoose = findViewById(R.id.btn_choose_file);
        btnUpload = findViewById(R.id.btn_upload_file);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(UploadActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            200);
                    return;
                } else
                    showFileChooser();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }
        });
    }

    void upload() {
        loading.show();
        new ApiClient().uploadPhoto(prefHelper.getString(ID), selectedFile, new UploadResponse.UploadResponseCallback() {
            @Override
            public void onSuccess(UploadResponse uploadResponse) {
                loading.cancel();
                Toast.makeText(UploadActivity.this, "sukses", Toast.LENGTH_LONG).show();
                Intent startActivityIntent = new Intent(getApplicationContext(), MisiActivity.class);
                startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startActivityIntent);
            }

            @Override
            public void onError(String message) {
                loading.cancel();
                Toast.makeText(UploadActivity.this, "gagal " + message, Toast.LENGTH_LONG).show();
                Log.i(TAG, message);
            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose File.."), PICK_FILE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_FILE_REQUEST) {
                if (data == null) {
                    //no Data present
                    return;
                }

                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(this, selectedFileUri);
                Log.i(TAG, "Selected File Path:" + selectedFilePath);
                if (selectedFilePath != null && !selectedFilePath.equals("")) {
                    textViewFile.setText(selectedFilePath);
                    selectedFile = new File(selectedFilePath);
                } else {
                    Toast.makeText(this, "Gagal mengambil gambar", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                showFileChooser();
            } else {
                // User refused to grant permission.
                Toast.makeText(this, "anda tidak memberi izin", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
