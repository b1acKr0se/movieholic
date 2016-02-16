package b1ackr0se.io.movieholic.presenter.detail;

import java.util.List;

import b1ackr0se.io.movieholic.data.model.Image;
import b1ackr0se.io.movieholic.data.model.Movie;
import b1ackr0se.io.movieholic.data.model.Review;
import b1ackr0se.io.movieholic.data.model.Video;

public interface IMovieDetailView {
    void showMovieDetails(Movie movie);
    void showGallery(List<Image> images);
    void showVideo(List<Video> videos);
    void showReview(List<Review> reviews);
}
