package com.example.viewpagerdemo.app.profile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reaction {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("user_profile_pic_url")
    @Expose
    private String userProfilePicUrl;
    @SerializedName("user_name")
    @Expose
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserProfilePicUrl() {
        return userProfilePicUrl;
    }

    public void setUserProfilePicUrl(String userProfilePicUrl) {
        this.userProfilePicUrl = userProfilePicUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

