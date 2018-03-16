package com.blackswan.popularmovies.adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blackswan.popularmovies.R;
import com.blackswan.popularmovies.fragment.DetailPopularMoviesFragment;
import com.blackswan.popularmovies.model.Result;

import java.util.List;

/**
 * Created by Blackswan on 7/29/2017.
 */

public class VideoListAdapter2 extends RecyclerView.Adapter<VideoListAdapter2.MyViewHolder> {
    List<Result> videolist;
    Fragment frag;

    public VideoListAdapter2(DetailPopularMoviesFragment callback, List<Result> videolist) {
        frag = callback;
        this.videolist = videolist;
    }

    @Override
    public VideoListAdapter2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_adapter, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(VideoListAdapter2.MyViewHolder holder, final int position) {
        holder.txtname.setText(videolist.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + videolist.get(position).getKey()));
                frag.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return videolist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgvideo;
        TextView txtname;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgvideo = (ImageView) itemView.findViewById(R.id.imgvideo);
            txtname = (TextView) itemView.findViewById(R.id.txtname);

        }
    }
}
