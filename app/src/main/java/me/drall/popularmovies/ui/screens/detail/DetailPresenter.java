package me.drall.popularmovies.ui.screens.detail;

import javax.inject.Inject;
import me.drall.popularmovies.data.api.model.Movie;

/**
 * Created by me on 6/14/17.
 */

public class DetailPresenter implements DetailContract.Presenter {

    DetailContract.View view;
    Movie movie;

    @Inject
    public DetailPresenter(DetailContract.View view, Movie movie) {
        this.view = view;
        this.movie = movie;
    }

    @Override
    public void onBind() {
        view.showMovieTitle(movie.title());
        view.showMoviePoster("http://image.tmdb.org/t/p/w300"+movie.posterPath());
        view.showMovieDescription(movie.overview());
        view.showMovieReleaseDate(movie.releaseDate());
        view.showMovieVotingAverage(movie.voteAverage());
    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onMoviePreviewVideoClicked() {

    }
}
