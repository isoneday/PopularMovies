package com.blackswan.popularmovies.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blackswan.popularmovies.R;
import com.blackswan.popularmovies.model.Result;

import java.util.List;

/**
 * Created by Blackswan on 7/30/2017.
 */

class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.MyViewHolder> {
    List<Result> reviewlist;
    Fragment frag;

    public ReviewAdapter(DetailPopularMoviesFragment detailPopularMoviesFragment, List<Result> videolist) {
        frag = detailPopularMoviesFragment;
        this.reviewlist = videolist;

    }

    @Override
    public ReviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_adapter, parent, false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.MyViewHolder holder, int position) {
        holder.txtcontent.setText(reviewlist.get(position).getContent());
        holder.txtauthor.setText(reviewlist.get(position).getAuthor());
         }

    @Override
    public int getItemCount() {
        return reviewlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
      TextView txtauthor,txtcontent;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtauthor =(TextView) itemView.findViewById(R.id.txtauthor);
            txtcontent =(TextView) itemView.findViewById(R.id.txtcontent);
        }
    }
}
