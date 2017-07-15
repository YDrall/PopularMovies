package me.drall.popularmovies.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drall.popularmovies.GlideApp;
import me.drall.popularmovies.R;
import me.drall.popularmovies.data.api.model.Movie;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<Movie> moviesList;
    private Context context;
    private MovieClickListener MovieItem;

    public MovieListAdapter(@Nullable List<Movie> list, Context context) {
        if(list == null)
            this.moviesList = new ArrayList<>();
        else
            this.moviesList = list;
        this.context = context;
    }

    public MovieListAdapter(@Nullable List<Movie> list, Context context, MovieClickListener listener) {
        this(list,context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder view = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movies_list,parent,false));
        return view;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GlideApp.with(context)
                .load("http://image.tmdb.org/t/p/w300"+moviesList.get(position).posterPath())
                .centerCrop()
                .into(holder.imageView);
        holder.textView.setText(moviesList.get(position).title());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public void setMoviesList(List<Movie> list) {
        this.moviesList = list;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.poster)
        public ImageView imageView;

        @BindView(R.id.title)
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    /**
     *  interface definition for a callback when an item is clicked.
     */
    public interface MovieClickListener {
        void onItemClick();
    }

    /**
     *  interface definition for callbacks on click event of different items in view
     */
    public interface MovieItemsClickListener {
        void onPosterImageClicked();
        void onTitleClicked();
    }
}
