package com.blackswan.popularmovies.rest;

import com.blackswan.popularmovies.model.ReviewModel;
import com.blackswan.popularmovies.model.VideoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Blackswan on 7/28/2017.
 */

public interface RestAPI {
    @GET("/videos?api_key=fb8fcc8ac7c9bb716ac0546ef0a8efb4/")
    Call<VideoModel> getVideo();


    @GET("{id}/videos")
    Call<VideoModel> request_videos(
            @Path("id") String id,
            @Query("api_key") String api_key,
            @Query("language") String bahasa

    );

    @GET("{id}/reviews")
    Call<ReviewModel> request_review(
            @Path("id") String id,
            @Query("api_key") String api_key,
            @Query("language") String bahasa,
            @Query("page") String status


    );

}