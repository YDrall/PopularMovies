package me.drall.popularmovies.ui.screens.home;

import java.util.List;

import me.drall.popularmovies.data.api.model.Movie;
import me.drall.popularmovies.ui.base.BasePresenter;
import me.drall.popularmovies.ui.base.BaseView;

public class HomeContract {
    interface View extends BaseView {
        void showPopularMovies(List<Movie> movies);
        void showTopRatedMovies(List<Movie> movies);
        void showSortView(String[] choices, int currentSelection);
        void showProgressView();
        void hideProgressView();
        void showErrorView(String error);
        void showMovieDetail(Movie movie);
    }

    interface Presenter extends BasePresenter {
        void loadPopularMovies();
        void loadTopRatedMovies();
        void onSortItemSelected(int position);
        void onRefresh();
        void onListItemClicked(int which);
        void onSortMenuItemClicked();
    }
}
