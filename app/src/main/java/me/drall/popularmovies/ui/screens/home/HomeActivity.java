package me.drall.popularmovies.ui.screens.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drall.popularmovies.PopularMovieComponent;
import me.drall.popularmovies.R;
import me.drall.popularmovies.ui.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.home_frag)
    FrameLayout frameLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSetContentView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void onBindView() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_frag,HomeFragment.newInstance(),"home_fragment")
                .commit();
    }

    @Override
    protected void onCreateComponent(PopularMovieComponent component) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
