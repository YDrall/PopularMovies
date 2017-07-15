package me.drall.popularmovies.data.api;


import io.reactivex.Observable;
import retrofit2.http.GET;


public interface TmdbService {

    @GET("movie/popular")
    Observable<me.drall.popularmovies.data.api.model.response.popular.Response> getPopularMovies();

    @GET("movie/top_rated")
    Observable<me.drall.popularmovies.data.api.model.response.top_rated.Response> getTopRatedMovies();
}
