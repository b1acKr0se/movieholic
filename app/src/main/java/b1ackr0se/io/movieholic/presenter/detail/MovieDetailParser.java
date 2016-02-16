package b1ackr0se.io.movieholic.presenter.detail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import b1ackr0se.io.movieholic.data.model.Image;
import b1ackr0se.io.movieholic.data.model.Review;
import b1ackr0se.io.movieholic.data.model.Video;
import b1ackr0se.io.movieholic.util.Api;

public class MovieDetailParser {
    public static final String BACKDROP = "backdrops";
    public static final String IMAGE_FILE_PATH = "file_path";

    public static final String RESULTS = "results";
    public static final String KEY = "key";
    public static final String SITE = "site";

    public static final String AUTHOR = "author";
    public static final String CONTENT = "content";

    public static List<Image> getImageList(String json) throws JSONException {
        List<Image> list = new ArrayList<>();
        JSONObject object = new JSONObject(json);
        if (!object.isNull(BACKDROP)) {
            JSONArray array = object.getJSONArray(BACKDROP);
            for (int i = 0; i < array.length(); i++) {
                Image image = getIndividualImage(array.getJSONObject(i));
                if (image != null) list.add(image);
            }
        }
        return list;
    }

    public static Image getIndividualImage(JSONObject object) throws JSONException {
        if (!object.isNull(IMAGE_FILE_PATH)) {
            Image image = new Image();
            image.setFilePath(Api.BASE_BACKDROP_URL + object.getString(IMAGE_FILE_PATH));
            return image;
        }
        return null;
    }

    public static List<Video> getVideoList(String json) throws JSONException {
        List<Video> list = new ArrayList<>(); 
        JSONObject object = new JSONObject(json);
        if (!object.isNull(RESULTS)) {
            JSONArray array = object.getJSONArray(RESULTS);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                Video video = getIndividualVideo(jsonObject);
                if (video != null)
                    list.add(video);
            }
        }
        return list;
    }

    public static Video getIndividualVideo(JSONObject object) throws JSONException {
        Video video = new Video();
        if (!object.isNull(KEY)) {
            video.setId(object.getString(KEY));
            if (!object.isNull(SITE)) {
                video.setSite(object.getString(SITE));
                return video;
            }
        }
        return null;
    }


    public static List<Review> getReviewList(String json) throws JSONException {
        List<Review> list = new ArrayList<>();
        JSONObject object = new JSONObject(json);

        if (!object.isNull(RESULTS)) {
            JSONArray array = object.getJSONArray(RESULTS);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                Review review = getIndividualReview(jsonObject);
                if (review != null) list.add(review);
            }
        }
        return list;
    }

    public static Review getIndividualReview(JSONObject object) throws JSONException {
        Review review = new Review();
        if (!object.isNull(AUTHOR)) {
            review.setAuthor(object.getString(AUTHOR));
            if (!object.isNull(CONTENT)) {
                review.setContent(object.getString(CONTENT));
                return review;
            }
        }
        return null;
    }
}

