package b1ackr0se.io.movieholic.presenter;

import java.util.ArrayList;

import b1ackr0se.io.movieholic.data.model.Movie;
import rx.Observable;

public interface IMovieFetcher {
    Observable<ArrayList<Movie>> fetchMovies();
}
