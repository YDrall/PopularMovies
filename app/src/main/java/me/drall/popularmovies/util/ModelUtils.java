package me.drall.popularmovies.util;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import me.drall.popularmovies.data.api.model.Movie;
import me.drall.popularmovies.data.api.model.response.popular.ResultsItem;

public class ModelUtils {

    public static List<Movie> convertPopularMovies(List<ResultsItem> popularList) {
        List<Movie> list = new ArrayList<>();
        for(ResultsItem item:popularList) {
            list.add(Movie.create(
                item.overview(),item.originalTitle(),item.posterPath(),item.backdropPath(),
                item.releaseDate(),item.popularity(),item.id(),item.adult(),item.title(),item.voteAverage()
            ));
        }

        return list;
    }

    public static List<Movie> convertTopMovies(List<me.drall.popularmovies.data.api.model.response.top_rated.ResultsItem> popularList) {
        List<Movie> list = new ArrayList<>();
        for(me.drall.popularmovies.data.api.model.response.top_rated.ResultsItem item:popularList) {
            list.add(Movie.create(
                item.overview(),item.originalTitle(),item.posterPath(),item.backdropPath(),
                item.releaseDate(),item.popularity(),item.id(),item.adult(),item.title(), item.voteAverage()
            ));
        }

        return list;
    }
}
