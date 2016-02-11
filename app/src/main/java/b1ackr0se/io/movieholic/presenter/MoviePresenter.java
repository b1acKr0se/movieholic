package b1ackr0se.io.movieholic.presenter;

import java.util.ArrayList;

import b1ackr0se.io.movieholic.data.model.Movie;
import b1ackr0se.io.movieholic.util.Option;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class MoviePresenter implements IMoviePresenter{

    private IMovieListingFragment mMovieListingFragment;
    private MovieFetcher mMovieFetcher;

    public MoviePresenter(IMovieListingFragment movieListingFragment, Option option) {
        this.mMovieListingFragment = movieListingFragment;
        this.mMovieFetcher = new MovieFetcher(option);
    }

    @Override
    public Subscription displayData() {
        return mMovieFetcher.fetchMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mMovieListingFragment.showLoading();
                    }
                })
                .subscribe(new Subscriber<ArrayList<Movie>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mMovieListingFragment.hideLoading();
                        mMovieListingFragment.showError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(ArrayList<Movie> movies) {
                        mMovieListingFragment.hideLoading();
                        mMovieListingFragment.showMovies(movies);
                    }
                });
    }
}
