package com.slava.vkvideofeed.ui.main;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.slava.vkvideofeed.R;
import com.slava.vkvideofeed.model.getnewsfeed.Item;
import com.slava.vkvideofeed.model.getnewsfeed.NewsFeedData;
import com.slava.vkvideofeed.model.getnewsfeed.NewsFeedInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VideoRvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<NewsFeedData> videoInfos;
    private RecyclerViewClickListener recyclerViewClickListener;

    VideoRvAdapter(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
        this.videoInfos = new ArrayList<>();
    }

    void handleVideoInfoResponse(NewsFeedInfo videoInfo) {
        for (int i = 0; i < videoInfo.getResponse().getItems().size(); i++) {
            Item item = videoInfo.getResponse().getItems().get(i);
            for (int j = 0; j < item.getVideo().getCount(); j++) {
                videoInfos.add(item.getVideo().getItems().get(j));
            }
        }
        notifyDataSetChanged();
    }

    List<NewsFeedData> getListFromAdapter(){
        return videoInfos;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        RecyclerView.ViewHolder vh;
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


    class VideoInfoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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

        void setVideo(NewsFeedData video) {
            tvName.setText(video.getTitle());

            tvLength.setText(DateUtils.formatElapsedTime(video.getDuration()));
            tvName.requestLayout();
            String path = video.getPhoto130();
            Glide.with(tvName.getContext())
                    .load(path)
                    .into(imageView);
        }

        @Override
        public void onClick(View v) {
            recyclerViewClickListener.recyclerViewListClicked(v,this.getLayoutPosition());
        }
    }

    public interface RecyclerViewClickListener {
        void recyclerViewListClicked(View v, int position);
    }

}
