package me.drall.popularmovies.ui.screens.home;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.drall.popularmovies.data.api.TmdbService;
import me.drall.popularmovies.data.api.model.Movie;
import me.drall.popularmovies.data.api.model.response.popular.Response;
import me.drall.popularmovies.util.ModelUtils;
import timber.log.Timber;

public class HomePresenter implements HomeContract.Presenter {

    TmdbService api;
    HomeContract.View view;
    private List<Movie> movies;
    private String[] choices = {"Popularity","Top Rated"};
    private int selectedChoice;
    private CompositeDisposable subscriptions;

    @Inject
    HomePresenter(TmdbService api, HomeContract.View view) {
        this.api = api;
        this.view = view;
    }

    @Override
    public void onBind() {
        subscriptions = new CompositeDisposable();
        selectedChoice = 0;
        loadPopularMovies();
    }

    @Override
    public void onDetach() {
        if(!subscriptions.isDisposed()) {
            subscriptions.dispose();
        }
    }

    @Override
    public void loadPopularMovies() {
        view.showProgressView();
        subscriptions.add(api.getPopularMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(@NonNull Response response) throws Exception {
                        Timber.d(response.toString());
                        movies = ModelUtils.convertPopularMovies(response.results());
                        view.showPopularMovies(movies);
                        view.hideProgressView();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Timber.d(throwable);
                        view.showErrorView(throwable.getMessage());
                        view.hideProgressView();
                    }
                }));

    }

    @Override
    public void loadTopRatedMovies() {
        view.showProgressView();
        subscriptions.add(api.getTopRatedMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<me.drall.popularmovies.data.api.model.response.top_rated.Response>() {
                    @Override public void accept(@NonNull
                        me.drall.popularmovies.data.api.model.response.top_rated.Response response)
                        throws Exception {
                        Timber.d(response.toString());
                        movies = ModelUtils.convertTopMovies(response.results());
                        view.showPopularMovies(movies);
                        view.hideProgressView();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Timber.d(throwable);
                        view.showErrorView(throwable.getMessage());
                        view.hideProgressView();
                    }
                }));

    }

    @Override
    public void onSortItemSelected(int which) {
        if(selectedChoice==which)
            return;

        selectedChoice = which;
        switch (selectedChoice) {
            case 0:
                loadPopularMovies();
                break;
            case 1:
                loadTopRatedMovies();
        }
    }

    @Override
    public void onRefresh() {
      if(selectedChoice==0) {
        loadPopularMovies();
      }
      else if(selectedChoice==1) {
        loadTopRatedMovies();
      }
    }

    @Override
    public void onListItemClicked(int position) {
        view.showMovieDetail(movies.get(position));
    }

    @Override
    public void onSortMenuItemClicked() {
        view.showSortView(choices,selectedChoice);
    }
}
