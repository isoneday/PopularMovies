package com.blackswan.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by iswandi on 7/07/2017.
 */

public class PopularMoviesModel implements Parcelable {
    String imageURl, release_date, title, rating, synopsis, backUrl;
    int id;

    public PopularMoviesModel(String Title, String Imageurl, String Release_date, String Synopsis, String Rating, String PosterUrl, int Id) {
        imageURl = Imageurl;
        title = Title;
        release_date = Release_date;
        synopsis = Synopsis;
        rating = Rating;
        id = Id;
        backUrl = PosterUrl;
    }



    private PopularMoviesModel(Parcel in) {
        imageURl = in.readString();
        title = in.readString();

        release_date = in.readString();
        synopsis = in.readString();
        rating = in.readString();
        id = in.readInt();
        backUrl = in.readString();
    }

    public String getImageurl() {
        return imageURl;
    }

    public String getBackPoster() {
        return backUrl;
    }

    public String getTitle() {
        return title;
    }
    public String getRelease_date() {
        return release_date;
    }
    public String getSynopsis() {
        return synopsis;
    }
    public String getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageURl);
        parcel.writeString(title);
        parcel.writeString(release_date);
        parcel.writeString(synopsis);
        parcel.writeString(rating);
        parcel.writeInt(id);
        parcel.writeString(backUrl);
    }

    public final static Creator<PopularMoviesModel> CREATOR = new Creator<PopularMoviesModel>() {
        @Override
        public PopularMoviesModel createFromParcel(Parcel parcel) {
            return new PopularMoviesModel(parcel);
        }

        @Override
        public PopularMoviesModel[] newArray(int i) {
            return new PopularMoviesModel[i];
        }
    };
}
