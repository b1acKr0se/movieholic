package b1ackr0se.io.movieholic.presenter;

import java.util.ArrayList;

import b1ackr0se.io.movieholic.data.model.Movie;

public interface IMovieListingFragment {
    void showLoading();
    void hideLoading();
    void showError(String error);
    void showMovies(ArrayList<Movie> movies);
    void onMovieClicked(Movie movie);
}
