package b1ackr0se.io.movieholic.presenter;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;

import b1ackr0se.io.movieholic.data.model.Movie;
import b1ackr0se.io.movieholic.util.Api;
import b1ackr0se.io.movieholic.util.JsonParser;
import rx.Observable;
import rx.functions.Func0;

public class MovieFetcher implements IMovieFetcher {

    private final OkHttpClient mOkhttpClient = new OkHttpClient();
    private boolean mIsMovie;

    public MovieFetcher(boolean mIsMovie) {
        this.mIsMovie = mIsMovie;
    }

    @Override
    public Observable<ArrayList<Movie>> fetchMovies() {
        return Observable.defer(new Func0<Observable<ArrayList<Movie>>>() {
            @Override
            public Observable<ArrayList<Movie>> call() {
                try {
                    return Observable.just(get(mIsMovie));
                }catch (Exception ex) {
                    return Observable.error(ex);
                }
            }
        });
    }

    private ArrayList<Movie> get(boolean isMovie) throws Exception {
        String END_POINT = isMovie ? Api.POPULAR_MOVIES : Api.POPULAR_TV_SHOWS;
        Request request = new Request.Builder()
                .url(END_POINT)
                .build();
        Response response = mOkhttpClient.newCall(request).execute();
        String jsonResult = response.body().string();
        return JsonParser.parse(jsonResult);
    }
}
