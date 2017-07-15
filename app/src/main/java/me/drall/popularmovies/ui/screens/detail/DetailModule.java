package me.drall.popularmovies.ui.screens.detail;

import dagger.Module;
import dagger.Provides;
import me.drall.popularmovies.data.api.model.Movie;

@Module
public class DetailModule {

    private final DetailContract.View view;
    private final Movie movie;

    public DetailModule(DetailContract.View view, Movie movie) {
        this.view = view;
        this.movie=movie;
    }

    @Provides
    @DetailScope
    DetailContract.View providesView() {
        return this.view;
    }

    @Provides
    @DetailScope
    DetailPresenter providesDetailPresenter(DetailContract.View view) {
        return new DetailPresenter(view,movie);
    }

}
