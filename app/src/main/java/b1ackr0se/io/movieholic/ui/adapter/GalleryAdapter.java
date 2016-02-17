package b1ackr0se.io.movieholic.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import b1ackr0se.io.movieholic.R;
import b1ackr0se.io.movieholic.data.model.Image;
import b1ackr0se.io.movieholic.presenter.detail.IMovieDetailView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Context mContext;
    private List<Image> mList;
    private IMovieDetailView mMovieDetailView;

    public GalleryAdapter(Context context, List<Image> list, IMovieDetailView movieDetailView) {
        this.mContext = context;
        this.mList = list;
        this.mMovieDetailView = movieDetailView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gallery, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.mImage = mList.get(position);
        Glide.with(mContext).load(mList.get(position).getFilePath()).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Image mImage;
        @Bind(R.id.image_gallery)ImageView mImageView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {
            mMovieDetailView.onGalleryImageClicked(v, mImage);
        }
    }
}
