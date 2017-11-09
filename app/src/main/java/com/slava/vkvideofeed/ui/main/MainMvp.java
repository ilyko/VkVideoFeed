package com.slava.vkvideofeed.ui.main;

import com.slava.vkvideofeed.model.getnewsfeed.NewsFeedInfo;
import com.slava.vkvideofeed.ui.base.MvpView;

public interface MainMvp {
    interface View extends MvpView {
        void handleNewsFeedResponse(NewsFeedInfo videoInfo);
        void handleVideoResponse(String url);
    }

    interface Presenter {
        void vkNewsFeedVideos(int startFrom);
        void vkVideoGet(int idOwner, int idVideo);
    }
}
