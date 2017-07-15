package me.drall.popularmovies.ui.screens.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.drall.popularmovies.PopularMovieComponent;
import me.drall.popularmovies.R;
import me.drall.popularmovies.ui.base.BaseActivity;
import me.drall.popularmovies.ui.screens.home.HomeFragment;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.detail_activity_frame) FrameLayout frameLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSetContentView() {
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void onBindView() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.detail_activity_frame, DetailFragment.newInstance(),"detail_fragment")
            .commit();
    }

    @Override
    protected void onCreateComponent(PopularMovieComponent component) {
    }

    @Override public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
