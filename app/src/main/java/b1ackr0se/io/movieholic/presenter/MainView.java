package b1ackr0se.io.movieholic.presenter;

import java.util.ArrayList;

import b1ackr0se.io.movieholic.data.model.Movie;

public interface MainView {
    void setData(ArrayList<Movie> list);
    void showMessage(String message);
}
