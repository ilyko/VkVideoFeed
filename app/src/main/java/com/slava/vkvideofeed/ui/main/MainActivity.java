package com.slava.vkvideofeed.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.slava.vkvideofeed.R;
import com.slava.vkvideofeed.model.getnewsfeed.NewsFeedData;
import com.slava.vkvideofeed.model.getnewsfeed.NewsFeedInfo;
import com.slava.vkvideofeed.ui.base.BaseActivity;
import com.slava.vkvideofeed.ui.base.EndlessRecyclerOnScroll;
import com.slava.vkvideofeed.ui.videoview.VideoViewActivity;
import com.slava.vkvideofeed.ui.login.LoginActivity;
import com.vk.sdk.VKSdk;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainMvp.View, VideoRvAdapter.RecyclerViewClickListener {
    public static int LAYOUT = R.layout.activity_main;

    VideoRvAdapter videoRvAdapter;
    private int mLoadedItems = 0;

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
        initRv();
        progressBar.setVisibility(View.VISIBLE);
        presenter.vkNewsFeedVideos(mLoadedItems);

    }

    void initRv() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        videoRvAdapter = new VideoRvAdapter(this);
        recyclerView.setAdapter(videoRvAdapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScroll() {
            @Override
            public void onLoadMore() {
                progressBar.setVisibility(View.VISIBLE);
                presenter.vkNewsFeedVideos(mLoadedItems);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout: {
                VKSdk.logout();
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void handleNewsFeedResponse(NewsFeedInfo videoInfo) {
        videoRvAdapter.handleVideoInfoResponse(videoInfo);
        mLoadedItems += videoInfo.getResponse().getItems().size();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void handleVideoResponse(String url) {
        Intent intent = new Intent(this, VideoViewActivity.class);
        intent.putExtra(VIDEO_PATH, url);
        startActivity(intent);
    }


    @Override
    public void recyclerViewListClicked(View v, int position) {
        List<NewsFeedData> videoData = videoRvAdapter.getListFromAdapter();
        presenter.vkVideoGet(videoData.get(position).getOwnerId(), videoData.get(position).getId());
    }
}
