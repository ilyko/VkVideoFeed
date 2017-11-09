package com.slava.vkvideofeed.model.getnewsfeed;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsFeed {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("items")
    @Expose
    private List<NewsFeedData> items = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<NewsFeedData> getItems() {
        return items;
    }

    public void setItems(List<NewsFeedData> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "NewsFeed{" +
                "count=" + count +
                ", items=" + items +
                '}';
    }
}

