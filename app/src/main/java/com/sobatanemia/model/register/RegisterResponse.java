
package com.sobatanemia.model.register;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponse implements Serializable
{

    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("user")
    @Expose
    public User user;

    public interface RegisterResponeCallback{
        void onSuccess(RegisterResponse registerResponse);
        void onError(String message);
    }
}
