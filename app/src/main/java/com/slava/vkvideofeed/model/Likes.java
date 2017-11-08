package com.slava.vkvideofeed.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Likes {

    @SerializedName("user_likes")
    @Expose
    private Integer userLikes;
    @SerializedName("count")
    @Expose
    private Integer count;

    public Integer getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(Integer userLikes) {
        this.userLikes = userLikes;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
