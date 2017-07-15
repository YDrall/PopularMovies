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
