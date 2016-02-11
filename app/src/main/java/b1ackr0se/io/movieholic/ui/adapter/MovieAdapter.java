package b1ackr0se.io.movieholic.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;

import b1ackr0se.io.movieholic.R;
import b1ackr0se.io.movieholic.data.model.Movie;
import b1ackr0se.io.movieholic.presenter.IMovieListingFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<Movie> mMovieList;
    private Context mContext;
    private IMovieListingFragment mMovieListingFragment;

    public MovieAdapter(Context context, ArrayList<Movie> list, IMovieListingFragment movieListingFragment) {
        this.mContext = context;
        this.mMovieList = list;
        this.mMovieListingFragment = movieListingFragment;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.image_poster)ImageView moviePoster;
        @Bind(R.id.view_background)View titleBackground;
        @Bind(R.id.text_name)TextView movieName;

        public Movie mMovie;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            mMovieListingFragment.onMovieClicked(mMovie);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.mMovie = mMovieList.get(position);
        holder.movieName.setText(holder.mMovie.getTitle());
        Glide.with(mContext).load(holder.mMovie.getPosterPath())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(new BitmapImageViewTarget(holder.moviePoster) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);
                        Palette.from(resource).generate(new Palette.PaletteAsyncListener() {
                            @Override
                            public void onGenerated(Palette palette) {
                                holder.titleBackground.setBackgroundColor(
                                        palette.getVibrantColor(ContextCompat.getColor(mContext, R.color.black_translucent))
                                );
                            }
                        });
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }
}
