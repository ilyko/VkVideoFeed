package com.slava.vkvideofeed.ui.main;

import com.slava.vkvideofeed.model.VideoInfo;
import com.slava.vkvideofeed.ui.base.MvpView;

/**
 * Created by slava on 08.11.17.
 */

public interface MainMvp {
    interface View extends MvpView {
        void handleVideoInfoResponse(VideoInfo videoInfo);
        void startFullScreenActivity(String url);
    }

    interface Presenter {
        void vkGetVideosInfo(int startFrom);
        void getVideoPath(String url);
    }
}
