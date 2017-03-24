package com.example.viewpagerdemo.app.profile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponseModel {

    @SerializedName("reactions")
    @Expose
    private List<Reaction> reactions = null;
    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}