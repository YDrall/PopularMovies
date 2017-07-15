/*
 *  Copyright (C) 2017 Yogesh Drall
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 *
 */

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
