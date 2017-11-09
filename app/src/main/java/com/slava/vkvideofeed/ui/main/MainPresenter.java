package com.slava.vkvideofeed.ui.main;

import com.google.gson.Gson;

import com.slava.vkvideofeed.model.getnewsfeed.NewsFeedInfo;
import com.slava.vkvideofeed.model.getvideo.VideoInfo;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import javax.inject.Inject;

public class MainPresenter implements MainMvp.Presenter {

    private MainMvp.View view;

    @Inject
    Gson gson;

    @Inject
    MainPresenter(MainMvp.View view) {
        this.view = view;
    }

    @Override
    public void vkNewsFeedVideos(int startFrom) {
        VKRequest request = new VKRequest("newsfeed.get",
                VKParameters.from(VKApiConst.FILTERS, "video", "start_from", startFrom, "count", 10));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                view.handleNewsFeedResponse(gson.fromJson(String.valueOf(response.json),
                        NewsFeedInfo.class));
            }
        });
    }

    @Override
    public void vkVideoGet(int idOwner, int idVideo) {
        VKRequest request = new VKRequest("video.get",
                VKParameters.from("videos", idOwner+"_"+idVideo));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                VideoInfo videoInfo = gson.fromJson(String.valueOf(response.json), VideoInfo.class);
                view.handleVideoResponse(videoInfo.getResponse().getItems().get(0).getPlayer());
            }
        });
    }
}
