package me.drall.popularmovies.ui.screens.home;

import dagger.Module;
import dagger.Provides;
import me.drall.popularmovies.data.api.TmdbService;

@Module
public class HomeModule {

    private final HomeContract.View view;

    public HomeModule(HomeContract.View view) {
        this.view = view;
    }

    @Provides
    @HomeScope
    HomeContract.View providesView() {
        return view;
    }

    @Provides
    @HomeScope
    HomeContract.Presenter providesPresenter(TmdbService service, HomeContract.View view) {
        return new HomePresenter(service,view);
    }

}
