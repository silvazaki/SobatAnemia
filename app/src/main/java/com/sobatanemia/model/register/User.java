
package com.sobatanemia.model.register;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Serializable
{

    @SerializedName("nama")
    @Expose
    public String nama;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("email")
    @Expose
    public Object email;
    @SerializedName("role_id")
    @Expose
    public Integer roleId;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("id")
    @Expose
    public Integer id;
    private final static long serialVersionUID = -9177360737256889140L;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
