package me.drall.popularmovies.data.api.model.response.top_rated;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class ResultsItem{

	@SerializedName("overview")
	public abstract String overview();

	@SerializedName("original_language")
	public abstract String originalLanguage();

	@SerializedName("original_title")
	public abstract String originalTitle();

	@SerializedName("video")
	public abstract boolean video();

	@SerializedName("title")
	public abstract String title();

	@SerializedName("genre_ids")
	public abstract List<Integer> genreIds();

	@Nullable
	@SerializedName("poster_path")
	public abstract String posterPath();

	@Nullable
	@SerializedName("backdrop_path")
	public abstract String backdropPath();

	@SerializedName("release_date")
	public abstract String releaseDate();

	@SerializedName("popularity")
	public abstract double popularity();

	@SerializedName("vote_average")
	public abstract double voteAverage();

	@SerializedName("id")
	public abstract int id();

	@SerializedName("adult")
	public abstract boolean adult();

	@SerializedName("vote_count")
	public abstract int voteCount();

	public static TypeAdapter<ResultsItem> typeAdapter(Gson gson) {
		return new AutoValue_ResultsItem.GsonTypeAdapter(gson);
	}
}