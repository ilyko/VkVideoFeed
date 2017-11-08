package com.slava.vkvideofeed.ui.main;

import com.google.gson.Gson;
import com.slava.vkvideofeed.model.VideoInfo;
import com.slava.vkvideofeed.util.LogUtil;
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
    public MainPresenter(MainMvp.View view) {
        this.view = view;
    }

    @Override
    public void vkGetVideosInfo(int startFrom) {
        LogUtil.info(this, "HERE 1");
        VKRequest request = new VKRequest("newsfeed.get",
                VKParameters.from(VKApiConst.FILTERS, "video", "start_from", startFrom, "count", 10));
        LogUtil.info(this, "start from: " + startFrom);
        LogUtil.info(this, "HERE 2");
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                LogUtil.info(this, response.json.toString());
                VideoInfo videoInfo = gson.fromJson(String.valueOf(response.json), VideoInfo.class);
                LogUtil.info(this, videoInfo.toString());
                view.handleVideoInfoResponse(gson.fromJson(String.valueOf(response.json), VideoInfo.class));
            }
        });
    }

    @Override
    public void getVideoPath(String url) {
        view.startFullScreenActivity(url);
    }
}
