package me.drall.popularmovies.data.api.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Movie implements Parcelable{

    public abstract String overview();

    public abstract String originalTitle();

    public abstract String title();

    @Nullable
    public abstract String posterPath();

    @Nullable
    public abstract String backdropPath();

    public abstract String releaseDate();

    public abstract double popularity();

    public abstract int id();

    public abstract boolean adult();

    public abstract double voteAverage();

    public static Movie create(String overview,String originalTitle, String posterPath, String backdropPath,
        String releaseDate, double popularity, int id, boolean adult, String title, double voteAverage) {
        return new AutoValue_Movie(overview,originalTitle,title,posterPath,backdropPath,releaseDate,popularity,id,adult,voteAverage);
    }

}
