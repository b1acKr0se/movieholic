package b1ackr0se.io.movieholic.presenter.listing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import b1ackr0se.io.movieholic.data.model.Movie;
import b1ackr0se.io.movieholic.util.Api;

public class JsonParser {

    public static final String RESULTS = "results";
    public static final String ID = "id";
    public static final String OVERVIEW = "overview";
    public static final String RELEASE_DATE = "release_date";
    public static final String TITLE = "title";
    public static final String NAME = "name";
    public static final String BACKDROP_PATH = "backdrop_path";
    public static final String VOTE_AVERAGE = "vote_average";
    public static final String POSTER_PATH = "poster_path";

    public static ArrayList<Movie> parse(String json) throws JSONException {
        ArrayList<Movie> list = new ArrayList<>();
        JSONObject object = new JSONObject(json);

        if(!object.isNull(RESULTS)) {
            JSONArray array = object.getJSONArray(RESULTS);

            for(int i = 0; i< array.length(); i++) {
                list.add(parseMovie(array.getJSONObject(i)));
            }
        }
        return list;
    }

    public static Movie parseMovie(JSONObject object) throws JSONException {
        Movie movie = new Movie();

        if(!object.isNull(ID))
        {
            movie.setId(object.getString(ID));
        }

        if(!object.isNull(OVERVIEW))
            movie.setOverview(object.getString(OVERVIEW));

        if(!object.isNull(RELEASE_DATE))
            movie.setReleaseDate(String.valueOf(object.get(OVERVIEW)));

        if(!object.isNull(TITLE))
            movie.setTitle(object.getString(TITLE));

        if(!object.isNull(BACKDROP_PATH))
            movie.setBackdropPath(Api.BASE_BACKDROP_URL + object.getString(BACKDROP_PATH));

        if(!object.isNull(POSTER_PATH))
            movie.setPosterPath(Api.BASE_POSTER_URL + object.getString(POSTER_PATH));

        if(!object.isNull(TITLE))
            movie.setVoteAverage(object.getDouble(VOTE_AVERAGE));

        if(!object.isNull(NAME))
            movie.setOriginalTitle(object.getString(NAME));

        return movie;
    }

}
