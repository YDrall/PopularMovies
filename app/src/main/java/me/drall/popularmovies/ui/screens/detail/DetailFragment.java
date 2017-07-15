package me.drall.popularmovies.ui.screens.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drall.popularmovies.GlideApp;
import me.drall.popularmovies.PopularMovieComponent;
import me.drall.popularmovies.R;

import me.drall.popularmovies.data.api.model.Movie;
import me.drall.popularmovies.ui.base.BaseFragment;
import me.drall.popularmovies.ui.screens.home.HomeFragment;
import org.w3c.dom.Text;

public class DetailFragment extends BaseFragment  implements DetailContract.View {

    @Inject
    DetailPresenter detailPresenter;

    @BindView(R.id.fragment_detail_textView_title)
    TextView titleView;

    @BindView(R.id.fragment_detail_textView_description)
    TextView descriptionView;

    @BindView(R.id.fragment_detail_imageView_posterImage)
    ImageView posterView;

    @BindView(R.id.fragment_detail_imageView_previewVideo)
    ImageView previewVideoView;

    @BindView(R.id.fragment_detail_textView_releaseDate) TextView releaseDateView;

    @BindView(R.id.fragment_detail_textView_voteAverage) TextView voteAverageView;


    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    protected void onCreateComponent(PopularMovieComponent component) {
        DaggerDetailComponent.builder()
                .detailModule(new DetailModule(this,
                    (Movie)getActivity().getIntent().getParcelableExtra(HomeFragment.MOVIE_EXTRA)))
                .build().inject(this);
    }

    @Override
    protected void onBindView(View source) {
        ButterKnife.bind(this,source);
    }

    @Override
    protected View onSetContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        posterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailPresenter.onMoviePreviewVideoClicked();
            }
        });

        detailPresenter.onBind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detailPresenter.onDetach();
    }

    @Override
    public void showMovieTitle(String title) {
        titleView.setText(title);
    }

    @Override
    public void showMovieDescription(String description) {
        descriptionView.setText(description);
    }

    @Override
    public void showMoviePoster(String path) {
        GlideApp.with(this)
                .load(path)
                .centerCrop()
                .into(posterView);
    }

    @Override
    public void showMoviePreviewVideo(String path) {
        GlideApp.with(this)
                .load(path)
                .centerCrop()
                .into(previewVideoView);
    }

    @Override public void showMovieReleaseDate(String date) {
        releaseDateView.setText(
            String.format(
                Locale.getDefault(),
                "%s: %s",getContext().getString(R.string.release_date),date));
    }

    @Override public void showMovieVotingAverage(double average) {
        voteAverageView.setText(
            String.format(
                Locale.getDefault(),
                "%s: %s",getContext().getString(R.string.voting_Average),String.valueOf(average)));
    }

}
