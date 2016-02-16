package b1ackr0se.io.movieholic.presenter.detail;

import android.util.Log;

import java.util.List;

import b1ackr0se.io.movieholic.data.model.Image;
import b1ackr0se.io.movieholic.data.model.Movie;
import b1ackr0se.io.movieholic.data.model.Review;
import b1ackr0se.io.movieholic.data.model.Video;
import b1ackr0se.io.movieholic.util.Option;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieDetailPresenter implements IMovieDetailPresenter {

    private IMovieDetailView mMovieDetailView;
    private MovieDetailFetcher mMovieDetailFetcher;

    public MovieDetailPresenter(IMovieDetailView movieDetailView, Option.Type option) {
        this.mMovieDetailView = movieDetailView;
        this.mMovieDetailFetcher = new MovieDetailFetcher(option);
    }


    @Override
    public void showDetails(Movie movie) {
        mMovieDetailView.showMovieDetails(movie);
    }

    @Override
    public Subscription showGallery(Movie movie) {
        return mMovieDetailFetcher.getImages(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Image>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Image> images) {
                        mMovieDetailView.showGallery(images);
                    }
                });
    }

    @Override
    public Subscription showVideos(Movie movie) {
        return mMovieDetailFetcher.getVideos(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Video>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getLocalizedMessage();
                    }

                    @Override
                    public void onNext(List<Video> videos) {
                        mMovieDetailView.showVideo(videos);
                    }
                });
    }

    @Override
    public Subscription showReviews(Movie movie) {
        return mMovieDetailFetcher.getReviews(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Review>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Review> reviews) {
                        mMovieDetailView.showReview(reviews);
                    }
                });
    }
}
