package me.drall.popularmovies.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.drall.popularmovies.PopularMovieApp;
import me.drall.popularmovies.PopularMovieComponent;


public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = onSetContentView(inflater, container, savedInstanceState);
        onBindView(root);
        onCreateComponent(PopularMovieApp.getComponent());
        return root;
    }

    /**
     * Add corresponding dagger modules to its parent component
     * @param component The parent component.
     */
    protected abstract void onCreateComponent(PopularMovieComponent component);

    /**
     *  Binding and initialization of variable can be done here.
     * @param source the view of fragment's UI or null if fragment provides no UI.
     */
    protected  abstract void onBindView(View source);

    /**
     * Called to initiate and inflate its user interface.
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI, or null.
     */
    protected abstract View onSetContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
}
