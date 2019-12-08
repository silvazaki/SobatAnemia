
package com.sobatanemia.model.upload;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable
{

    @SerializedName("nama")
    @Expose
    public String nama;
    @SerializedName("foto")
    @Expose
    public String foto;
    @SerializedName("tanggal")
    @Expose
    public String tanggal;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("id")
    @Expose
    public Integer id;
    private final static long serialVersionUID = 4128719799910230247L;

}
