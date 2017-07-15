package me.drall.popularmovies.ui.screens.detail;

import me.drall.popularmovies.ui.base.BasePresenter;
import me.drall.popularmovies.ui.base.BaseView;

/**
 * Created by me on 6/13/17.
 */

public class DetailContract {

    interface View extends BaseView {
        void showMovieTitle(String title);
        void showMovieDescription(String desciption);
        void showMoviePoster(String path);
        void showMoviePreviewVideo(String path);
        void showMovieReleaseDate(String date);
        void showMovieVotingAverage(double average);
    }

    interface Presenter extends BasePresenter {
        void onMoviePreviewVideoClicked();
    }
}
