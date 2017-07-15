package me.drall.popularmovies.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.drall.popularmovies.PopularMovieApp;
import me.drall.popularmovies.PopularMovieComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onSetContentView();
        onBindView();
        onCreateComponent(PopularMovieApp.getComponent());

    }

    protected abstract void onCreateComponent(PopularMovieComponent component);
    protected  abstract void onBindView();
    protected abstract void onSetContentView();
}
