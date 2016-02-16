package b1ackr0se.io.movieholic.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import b1ackr0se.io.movieholic.R;
import b1ackr0se.io.movieholic.data.model.Image;
import b1ackr0se.io.movieholic.data.model.Movie;
import b1ackr0se.io.movieholic.data.model.Review;
import b1ackr0se.io.movieholic.data.model.Video;
import b1ackr0se.io.movieholic.presenter.detail.IMovieDetailView;
import b1ackr0se.io.movieholic.presenter.detail.MovieDetailPresenter;
import b1ackr0se.io.movieholic.util.Option;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscription;

public class DetailFragment extends Fragment implements IMovieDetailView, View.OnClickListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.image_backdrop)
    ImageView mBackdropImageView;
    @Bind(R.id.text_name)
    TextView mMovieNameTextView;
    @Bind(R.id.text_overview)
    TextView mMovieOverviewTextView;

    @Bind(R.id.text_label_gallery) TextView mLabelGalleryTextView;
    @Bind(R.id.text_label_videos) TextView mLabelVideoTextView;
    @Bind(R.id.text_label_review)TextView mLabelReviewTextView;

    @Bind(R.id.horizontal_gallery)HorizontalScrollView mGalleryScrollView;
    @Bind(R.id.horizontal_videos)HorizontalScrollView mVideoScrollView;
    @Bind(R.id.layout_reviews)LinearLayout mReviewScrollView;

    @Bind(R.id.layout_gallery)
    LinearLayout mGalleryView;
    @Bind(R.id.layout_videos)
    LinearLayout mVideoView;

    private static Option.Type mFragmentType;
    private static Movie mMovie;
    private Subscription mGallerySubscription, mVideoSubscription, mReviewSubscription;
    private MovieDetailPresenter mMovieDetailPresenter;


    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment getInstance(Movie movie, Option.Type type) {
        mMovie = movie;
        mFragmentType = type;
        return new DetailFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("TYPE", mFragmentType);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            mMovieDetailPresenter = new MovieDetailPresenter(
                    this,
                    ((Option.Type) savedInstanceState.getSerializable("TYPE")));
        else
            mMovieDetailPresenter = new MovieDetailPresenter(this, mFragmentType);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        setupToolbar();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(mMovie != null) {
            mMovieDetailPresenter.showDetails(mMovie);

        }
    }

    @Override
    public void onDestroyView() {
        if(mGallerySubscription != null && !mGallerySubscription.isUnsubscribed())
            mGallerySubscription.unsubscribe();
        if(mVideoSubscription != null && !mVideoSubscription.isUnsubscribed())
            mVideoSubscription.unsubscribe();
        if(mReviewSubscription != null && !mReviewSubscription.isUnsubscribed())
            mReviewSubscription.unsubscribe();
        super.onDestroyView();
    }

    private void setupToolbar() {
        mCollapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        mCollapsingToolbarLayout.setTitle("Detail");
        mCollapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedState);
        mCollapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showMovieDetails(Movie movie) {
        Glide.with(getContext()).load(movie.getBackdropPath()).into(mBackdropImageView);
        if (movie.getTitle() != null)
            mMovieNameTextView.setText(movie.getTitle());
        else mMovieNameTextView.setText(movie.getOriginalTitle());
        mMovieOverviewTextView.setText(movie.getOverview());
        mGallerySubscription = mMovieDetailPresenter.showGallery(mMovie);
        mMovieDetailPresenter.showVideos(mMovie);
        mMovieDetailPresenter.showReviews(mMovie);
    }

    @Override
    public void showGallery(List<Image> images) {
        if (images.isEmpty()) {
            mLabelGalleryTextView.setVisibility(View.GONE);
            mGalleryScrollView.setVisibility(View.GONE);
            mGalleryView.setVisibility(View.GONE);
        } else {
            mLabelGalleryTextView.setVisibility(View.VISIBLE);
            mGalleryScrollView.setVisibility(View.VISIBLE);
            mGalleryView.setVisibility(View.VISIBLE);
            mGalleryView.removeAllViews();
            LayoutInflater inflater = getActivity().getLayoutInflater();

            for (Image image : images) {
                ViewGroup galleryContainer = (ViewGroup) inflater.inflate(R.layout.item_gallery, mGalleryView, false);
                ImageView thumbnailView = (ImageView) galleryContainer.findViewById(R.id.image_gallery);
                thumbnailView.setTag(image.getFilePath());
                thumbnailView.requestLayout();
                thumbnailView.setOnClickListener(this);
                Glide.with(getContext()).load(image.getFilePath()).into(thumbnailView);
                mGalleryView.addView(galleryContainer);
            }
        }
    }

    @Override
    public void showVideo(List<Video> videos) {
        if (videos.isEmpty()) {
            mLabelVideoTextView.setVisibility(View.GONE);
            mVideoScrollView.setVisibility(View.GONE);
            mVideoView.setVisibility(View.GONE);
        } else {
            mLabelVideoTextView.setVisibility(View.VISIBLE);
            mVideoScrollView.setVisibility(View.VISIBLE);
            mVideoView.setVisibility(View.VISIBLE);

            mVideoView.removeAllViews();

            LayoutInflater inflater = getActivity().getLayoutInflater();

            for (Video video : videos) {
                ViewGroup thumbContainer = (ViewGroup) inflater.inflate(R.layout.item_video, mVideoView, false);
                ImageView thumbView = (ImageView) thumbContainer.findViewById(R.id.image_video);
                thumbView.setTag(Video.getVideoUrl(video));
                thumbView.requestLayout();
                thumbView.setOnClickListener(this);
                Glide.with(getActivity()).load(Video.getThumbnail(video)).into(thumbView);
                mVideoView.addView(thumbContainer);
            }
        }
    }

    @Override
    public void showReview(List<Review> reviews) {
        if (reviews.isEmpty()) {
            mLabelReviewTextView.setVisibility(View.GONE);
            mReviewScrollView.setVisibility(View.GONE);
        } else {
            mLabelReviewTextView.setText(View.VISIBLE);
            mReviewScrollView.setVisibility(View.VISIBLE);

            mReviewScrollView.removeAllViews();

            LayoutInflater inflater = getActivity().getLayoutInflater();

            for (Review review : reviews) {
                ViewGroup reviewContainer = (ViewGroup) inflater.inflate(R.layout.item_review, mReviewScrollView, false);
                TextView reviewAuthor = (TextView) reviewContainer.findViewById(R.id.text_review_author);
                TextView reviewContent = (TextView) reviewContainer.findViewById(R.id.text_review_content);
                reviewAuthor.setText(review.getAuthor());
                reviewContent.setText(review.getContent());
                mReviewScrollView.addView(reviewContainer);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_gallery:
                break;
            case R.id.image_video:
                String videoUrl = (String) v.getTag();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoUrl));
                startActivity(intent);
                break;
        }
    }
}
