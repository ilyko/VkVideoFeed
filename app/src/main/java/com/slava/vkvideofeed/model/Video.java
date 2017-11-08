package com.slava.vkvideofeed.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("items")
    @Expose
    private List<VideoData> items = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<VideoData> getItems() {
        return items;
    }

    public void setItems(List<VideoData> items) {
        this.items = items;
    }

}

