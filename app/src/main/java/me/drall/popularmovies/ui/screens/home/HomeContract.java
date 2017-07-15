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
