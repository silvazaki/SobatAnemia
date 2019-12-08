package com.sobatanemia;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sobatanemia.model.login.LoginResponse;
import com.sobatanemia.model.register.RegisterResponse;
import com.sobatanemia.model.upload.UploadResponse;

import org.json.JSONObject;

import java.io.File;

public class ApiClient {

    public static final String BASE_URL = "http://sobatanemia.000webhostapp.com/api/v1/";
    private Gson gson;
    private String TAG = "hasil";

    public ApiClient() {
        this.gson = new GsonBuilder().create();
    }


    public void login(final String user, String password, final LoginResponse.LoginResponseCallback callback) {
        String requestUrl = BASE_URL + "user/login";

        AndroidNetworking.post(requestUrl)
                .addBodyParameter("username", user)
                .addBodyParameter("password", password)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        LoginResponse loginResponse = gson.fromJson(response.toString(), LoginResponse.class);
//                        System.out.println("ayo dong "+loginResponse.message);
                        if (loginResponse != null) {
                            callback.onSuccess(loginResponse);
                        } else {
                            callback.onError("Eror parsing data");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onError(anError.getMessage());
                    }
                });

    }

    public void daftar(final String user, String password, String nama, final RegisterResponse.RegisterResponeCallback callback) {
        String requestUrl = BASE_URL + "user/register";

        AndroidNetworking.post(requestUrl)
                .addBodyParameter("username", user)
                .addBodyParameter("nama", nama)
                .addBodyParameter("password", password)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        RegisterResponse registerResponse = gson.fromJson(response.toString(), RegisterResponse.class);
                        if (registerResponse != null) {
                            callback.onSuccess(registerResponse);
                        } else {
                            callback.onError("Eror parsing data");
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        callback.onError(anError.getMessage());
                    }
                });

    }

    public void uploadPhoto(String id, File file, final UploadResponse.UploadResponseCallback callback) {
        String requestUrl = BASE_URL + "laporan/" + id;
        Log.e(TAG, requestUrl);

        AndroidNetworking.upload(requestUrl)
                .addMultipartFile("foto", file)
                .setPriority(Priority.MEDIUM)
                .build()
                .setUploadProgressListener(new UploadProgressListener() {
                    @Override
                    public void onProgress(long bytesUploaded, long totalBytes) {
                        // do anything with progress
                    }
                })
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        UploadResponse response1 = gson.fromJson(response.toString(), UploadResponse.class);
                        if (response1 != null) {
                            callback.onSuccess(response1);
                        } else {
                            callback.onError("Cannot get Object");
                        }
                        Log.e(TAG, "onResponse: " + response.toString());
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        callback.onError(error.getErrorDetail());
                        Log.e(TAG, error.getErrorDetail());
                    }
                });
    }
}
