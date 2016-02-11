package b1ackr0se.io.movieholic.presenter;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.ArrayList;

import b1ackr0se.io.movieholic.data.model.Movie;
import b1ackr0se.io.movieholic.util.Api;
import b1ackr0se.io.movieholic.util.JsonParser;
import b1ackr0se.io.movieholic.util.Option;
import rx.Observable;
import rx.functions.Func0;

public class MovieFetcher implements IMovieFetcher {

    private final OkHttpClient mOkhttpClient = new OkHttpClient();
    private Option mOption;

    public MovieFetcher(Option option) {
        this.mOption = option;
    }

    @Override
    public Observable<ArrayList<Movie>> fetchMovies() {
        return Observable.defer(new Func0<Observable<ArrayList<Movie>>>() {
            @Override
            public Observable<ArrayList<Movie>> call() {
                try {
                    return Observable.just(get(mOption));
                }catch (Exception ex) {
                    return Observable.error(ex);
                }
            }
        });
    }

    private ArrayList<Movie> get(Option option) throws Exception {
        String END_POINT = "";

        switch (option) {
            case MOVIE_POPULAR:
                END_POINT = Api.POPULAR_MOVIES;
                break;
            case MOVIE_HIGHEST_RATING:
                END_POINT = Api.HIGHEST_RATED_MOVIES;
                break;
            case MOVIE_IN_THEATER:
                END_POINT = Api.CURRENT_MOVIES;
                break;
            case TV_POPULAR:
                END_POINT = Api.POPULAR_TV_SHOWS;
                break;
            case TV_HIGHEST_RATING:
                break;
            default:
                END_POINT = Api.POPULAR_MOVIES;
        }

        Request request = new Request.Builder()
                .url(END_POINT)
                .build();
        Response response = mOkhttpClient.newCall(request).execute();
        String jsonResult = response.body().string();
        return JsonParser.parse(jsonResult);
    }
}
