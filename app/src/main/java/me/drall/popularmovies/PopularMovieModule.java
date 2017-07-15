package me.drall.popularmovies;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class PopularMovieModule {
    PopularMovieApp mApplication;

    public PopularMovieModule(PopularMovieApp application) {
        this.mApplication = application;
    }

    @Provides
    @AppScope
    Application providesApplication() {
        return mApplication;
    }

}
