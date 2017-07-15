package me.drall.popularmovies.ui.screens.detail;

import dagger.Component;

@DetailScope
@Component(
        modules = DetailModule.class
)
interface DetailComponent {
    void inject(DetailActivity activity);
    void inject(DetailFragment fragment);
    DetailPresenter detailPresenter();
}
