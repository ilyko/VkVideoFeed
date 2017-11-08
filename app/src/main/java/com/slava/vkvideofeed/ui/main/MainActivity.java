package com.slava.vkvideofeed.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

import com.slava.vkvideofeed.R;
import com.slava.vkvideofeed.model.VideoData;
import com.slava.vkvideofeed.model.VideoInfo;
import com.slava.vkvideofeed.ui.base.BaseActivity;
import com.slava.vkvideofeed.ui.base.EndlessRecyclerOnScroll;
import com.slava.vkvideofeed.ui.fullscreen.FullscreenActivity;
import com.slava.vkvideofeed.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainMvp.View {
    public static int LAYOUT = R.layout.activity_main;

    VideoRvAdapter videoRvAdapter;
    private int mLoadedItems = 0;
    //private List<VideoData> videoData;

    @Inject
    MainPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.item_progress_bar)
    ProgressBar progressBar;
    public static final String VIDEO_PATH = "video_path";

    @Override
    public int getLayout() {
        return LAYOUT;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //videoData = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);
        videoRvAdapter = new VideoRvAdapter(presenter);
        recyclerView.setAdapter(videoRvAdapter);

        presenter.vkGetVideosInfo(mLoadedItems);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScroll() {
            @Override
            public void onLoadMore() {
                progressBar.setVisibility(View.VISIBLE);
                presenter.vkGetVideosInfo(mLoadedItems);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public void handleVideoInfoResponse(VideoInfo videoInfo) {
        LogUtil.info(this, videoInfo.toString());
        videoRvAdapter.handleVideoInfoResponse(videoInfo);
        LogUtil.info(this, "size:" + videoInfo.getResponse().getItems().size());
        mLoadedItems += videoInfo.getResponse().getItems().size();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void startFullScreenActivity(String url) {
        Intent intent = new Intent(this, FullscreenActivity.class);
        intent.putExtra(VIDEO_PATH, url);
        startActivity(intent);
    }
}
