package com.slava.vkvideofeed.model.getvideo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class VideoInfo {

    @SerializedName("response")
    @Expose
    private VideoResponse response;

    public VideoResponse getResponse() {
        return response;
    }

    public void setResponse(VideoResponse response) {
        this.response = response;
    }
}