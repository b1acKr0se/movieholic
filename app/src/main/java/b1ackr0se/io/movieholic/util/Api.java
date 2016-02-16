package b1ackr0se.io.movieholic.util;

/**
 * Contains const strings
 */
public class Api {

    public static final String API_KEY = "b78eb3b3e3e919500891f0526f344bf6";

    public static final String BASE_URL = "https://api.themoviedb.org/3";
    public static final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w342";
    public static final String BASE_BACKDROP_URL = "http://image.tmdb.org/t/p/w780";
    public static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/w1280";

    public static final String POPULAR_MOVIES = BASE_URL + "/discover/movie?sort_by=popularity.desc&api_key=" + API_KEY;
    public static final String HIGHEST_RATED_MOVIES = BASE_URL + "/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc&api_key=" + API_KEY;
    public static final String CURRENT_MOVIES = BASE_URL + getCurrentMovies() + API_KEY;
    public static final String POPULAR_TV_SHOWS = BASE_URL + "/discover/tv?sort_by=popularity.desc&api_key=" + API_KEY;
    public static final String MOVIE_TRAILERS = BASE_URL + "/movie/%s/videos?api_key=" + API_KEY;
    public static final String TV_TRAILERS = BASE_URL + "/tv/%s/videos?api_key=" + API_KEY;
    public static final String MOVIE_REVIEWS = BASE_URL + "movie/%s/reviews?api_key=" + API_KEY;


    private static String getCurrentMovies() {
        DateUtil dateUtil = new DateUtil();
        return "/discover/movie?primary_release_date.gte=" + dateUtil.getFirstDateString()
                + "&primary_release_date.lte=" + dateUtil.getSecondDateString()
                + "&api_key=";
    }

}
