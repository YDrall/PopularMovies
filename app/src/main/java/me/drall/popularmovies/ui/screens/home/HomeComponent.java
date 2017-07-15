package me.drall.popularmovies.ui.screens.home;

import dagger.Component;
import me.drall.popularmovies.PopularMovieComponent;

@HomeScope
@Component(
        dependencies = PopularMovieComponent.class,
        modules = HomeModule.class
)
interface HomeComponent {
    void inject(HomeActivity activity);
    void inject(HomeFragment fragment);
}
