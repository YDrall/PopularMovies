package me.drall.popularmovies;

import android.app.Application;

import dagger.Component;
import me.drall.popularmovies.data.api.TmdbApiModule;
import me.drall.popularmovies.data.api.TmdbService;

@AppScope
@Component(modules = {PopularMovieModule.class, TmdbApiModule.class} )
public interface PopularMovieComponent {
    TmdbService tmdbService();
    Application application();
}
