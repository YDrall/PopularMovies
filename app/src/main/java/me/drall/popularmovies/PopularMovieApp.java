package me.drall.popularmovies;


import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import me.drall.popularmovies.data.api.TmdbApiModule;
import timber.log.Timber;

public class PopularMovieApp extends Application {

    private static PopularMovieComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        component = DaggerPopularMovieComponent.builder()
                .popularMovieModule(new PopularMovieModule(this))
                .tmdbApiModule(new TmdbApiModule("https://api.themoviedb.org/3/"))
                .build();
    }

    public static PopularMovieComponent getComponent() {
        return component;
    }
}
