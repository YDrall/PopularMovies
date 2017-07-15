package me.drall.popularmovies.data.api.model.response.top_rated;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class Response{

	@SerializedName("page")
	public abstract int page();

	@SerializedName("total_pages")
	public abstract int totalPages();

	@SerializedName("results")
	public abstract List<ResultsItem> results();

	@SerializedName("total_results")
	public abstract int totalResults();

	public static TypeAdapter<Response> typeAdapter(Gson gson) {
		return new AutoValue_Response.GsonTypeAdapter(gson);
	}
}