package b1ackr0se.io.movieholic.presenter.detail;

import java.util.List;

import b1ackr0se.io.movieholic.data.model.Image;
import b1ackr0se.io.movieholic.data.model.Movie;
import rx.Subscription;

public interface IMovieDetailPresenter {
    void showDetails(Movie movie);
    Subscription showGallery(Movie movie);
    Subscription showVideos(Movie movie);
    Subscription showReviews(Movie movie);

}
