
package com.sobatanemia.model.login;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Serializable
{

    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Data data;

    public interface LoginResponseCallback {
        void onSuccess(LoginResponse loginResponse);
        void onError(String message);
    }
}
