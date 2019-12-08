
package com.sobatanemia.model.upload;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadResponse implements Serializable
{

    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("data")
    @Expose
    public Data data;

    public interface UploadResponseCallback{
        void onSuccess(UploadResponse uploadResponse);
        void onError(String message);
    }
}
