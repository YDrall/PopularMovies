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

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drall.popularmovies.PopularMovieComponent;
import me.drall.popularmovies.R;
import me.drall.popularmovies.data.api.model.Movie;
import me.drall.popularmovies.ui.adapter.MovieListAdapter;
import me.drall.popularmovies.ui.base.BaseFragment;
import me.drall.popularmovies.ui.listener.RecyclerItemClickListener;
import me.drall.popularmovies.ui.screens.detail.DetailActivity;

public class HomeFragment extends BaseFragment implements HomeContract.View {

    public static final String MOVIE_EXTRA = "MOVIE_EXTRA";

    @Inject
    HomeContract.Presenter presenter;

    @BindView(R.id.fragment_home_recyclerView_movies)
    RecyclerView moviesList;

    @BindView(R.id.fragment_home_swipeRefresh_movies)
    SwipeRefreshLayout swipeRefreshLayout;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
    }


    @Override
    public void showPopularMovies(List<Movie> movies) {
        ((MovieListAdapter)moviesList.getAdapter()).setMoviesList(movies);
    }

    @Override
    public void showTopRatedMovies(List<Movie> movies) {
        ((MovieListAdapter)moviesList.getAdapter()).setMoviesList(movies);
    }

    @Override
    public void showSortView(String[] choices, int currentSelection) {
        final AlertDialog dialog;
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_DayNight_Dialog_Alert);
        dialogBuilder.setTitle(getContext().getString(R.string.sort_by));
        dialogBuilder.setSingleChoiceItems(choices, currentSelection, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.onSortItemSelected(which);
                dialog.dismiss();
            }
        });

        dialog = dialogBuilder.create();
        dialog.show();
    }

    @Override
    public void showProgressView() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgressView() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showErrorView(String error) {
        //Snackbar.make(moviesList,error,Snackbar.LENGTH_SHORT);
        Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMovieDetail(Movie movie) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(MOVIE_EXTRA,movie);
        startActivity(intent);
    }

    @Override
    protected void onCreateComponent(PopularMovieComponent component) {
        DaggerHomeComponent.builder()
                .popularMovieComponent(component)
                .homeModule(new HomeModule(this)).build()
                .inject(this);
    }

    @Override
    protected void onBindView(View source) {
        ButterKnife.bind(this,source);
    }

    @Override
    protected View onSetContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefresh();
            }
        });
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),3, LinearLayoutManager.VERTICAL,false);
        moviesList.setLayoutManager(layoutManager);
        moviesList.setAdapter(new MovieListAdapter(null,getContext()));
        presenter.onBind();
        moviesList.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.poster:
                    case R.id.title:
                    case R.id.cover:
                    case R.id.item_movieList_parent:
                        presenter.onListItemClicked(position);
                        break;
                    default:
                        break;
                }
            }
        }));

        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_movie_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_movieList_sortBy:
                presenter.onSortMenuItemClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
