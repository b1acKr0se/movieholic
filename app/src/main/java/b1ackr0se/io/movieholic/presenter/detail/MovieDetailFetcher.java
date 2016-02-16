package b1ackr0se.io.movieholic.presenter.detail;

import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import b1ackr0se.io.movieholic.data.model.Image;
import b1ackr0se.io.movieholic.data.model.Review;
import b1ackr0se.io.movieholic.data.model.Video;
import b1ackr0se.io.movieholic.util.Api;
import b1ackr0se.io.movieholic.util.Option;
import rx.Observable;
import rx.functions.Func0;

public class MovieDetailFetcher implements IMovieDetailFetcher {
    private final OkHttpClient mOkHttpClient = new OkHttpClient();
    private Option.Type mType;

    public MovieDetailFetcher(Option.Type type) {
        this.mType = type;
    }


    @Override
    public Observable<List<Image>> getImages(final String id) {
        return Observable.defer(new Func0<Observable<List<Image>>>() {
            @Override
            public Observable<List<Image>> call() {
                try {
                    return Observable.just(get(id));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }

            private List<Image> get(String id) throws IOException, JSONException {
                String END_POINT = "";
                switch (mType) {
                    case MOVIE:
                        END_POINT = Api.MOVIE_IMAGES;
                        break;
                    case TV:
                        END_POINT = Api.TV_IMAGES;
                }
                String url = String.format(END_POINT, id);
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = mOkHttpClient.newCall(request).execute();
                String jsonResult = response.body().string();
                return MovieDetailParser.getImageList(jsonResult);
            }
        });
    }

    @Override
    public Observable<List<Video>> getVideos(final String id) {
        return Observable.defer(new Func0<Observable<List<Video>>>() {
            @Override
            public Observable<List<Video>> call() {
                try {
                    return Observable.just(get(id));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }

            private List<Video> get(String id) throws IOException, JSONException {
                String END_POINT = "";
                switch (mType) {
                    case MOVIE:
                        END_POINT = Api.MOVIE_TRAILERS;
                        break;
                    case TV:
                        END_POINT = Api.TV_TRAILERS;
                }
                String url = String.format(END_POINT, id);
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = mOkHttpClient.newCall(request).execute();
                String jsonResult = response.body().string();
                return MovieDetailParser.getVideoList(jsonResult);
            }
        });
    }

    @Override
    public Observable<List<Review>> getReviews(final String id) {
        return Observable.defer(new Func0<Observable<List<Review>>>() {
            @Override
            public Observable<List<Review>> call() {
                try {
                    return Observable.just(get(id));
                } catch (Exception e) {
                    return Observable.error(e);
                }
            }

            private List<Review> get(String id) throws IOException, JSONException {
                String END_POINT = "";
                switch (mType) {
                    case MOVIE:
                        END_POINT = Api.MOVIE_REVIEWS;
                        break;
                    case TV:
                        return null;
                }
                String url = String.format(END_POINT, id);
                Log.i("REVIEW_URL", url);
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = mOkHttpClient.newCall(request).execute();
                String jsonResult = response.body().string();
                return MovieDetailParser.getReviewList(jsonResult);
            }
        });
    }
}
