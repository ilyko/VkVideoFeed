package com.slava.vkvideofeed.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.slava.vkvideofeed.R;
import com.slava.vkvideofeed.model.Item;
import com.slava.vkvideofeed.model.VideoData;
import com.slava.vkvideofeed.model.VideoInfo;
import com.slava.vkvideofeed.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VideoRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<VideoData> videoInfos;
    private MainMvp.Presenter presenter;

    VideoRvAdapter(MainMvp.Presenter presenter) {
        this.presenter = presenter;
        this.videoInfos = new ArrayList<>();
        LogUtil.info(this, "presenter:" + presenter + " this.presenter" + this.presenter);
    }

    public void handleVideoInfoResponse(VideoInfo videoInfo) {
        for (int i = 0; i < videoInfo.getResponse().getItems().size(); i++) {
            Item item = videoInfo.getResponse().getItems().get(i);
            for (int j = 0; j < item.getVideo().getCount(); j++) {
                videoInfos.add(item.getVideo().getItems().get(j));
            }
        }
        notifyDataSetChanged();
    }


/*    private enum TYPE {LOADING, EMPTY, ARTIST}*/


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder vh = null;
        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video_info, parent, false);
        vh = new VideoInfoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((VideoInfoViewHolder) holder).setVideo(videoInfos.get(position));

    }

    @Override
    public int getItemCount() {
        return videoInfos.size();
    }

    class EmptyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.no_content)
        TextView tvNoContent;

        EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progress)
        ProgressBar progressBar;

        LoadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    class VideoInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_length)
        TextView tvLength;
        @BindView(R.id.image)
        ImageView imageView;

        VideoInfoViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        void setVideo(VideoData video) {
            tvName.setText(video.getTitle());
            tvLength.setText(video.getDuration().toString());

            tvName.requestLayout();
            String path = null;
            path = video.getPhoto130();
            Glide.with(tvName.getContext())
                    .load(path)
                    .into(imageView);
        }


        @Override
        public void onClick(View view) {
            presenter.getVideoPath("https://google.com/");
        }
    }

}
