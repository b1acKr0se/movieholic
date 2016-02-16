package b1ackr0se.io.movieholic.presenter.detail;

import java.util.List;
import b1ackr0se.io.movieholic.data.model.Image;
import b1ackr0se.io.movieholic.data.model.Review;
import b1ackr0se.io.movieholic.data.model.Video;
import rx.Observable;

public interface IMovieDetailFetcher {
    Observable<List<Image>> getImages(String id);
    Observable<List<Video>> getVideos(String id);
    Observable<List<Review>> getReviews(String id);
}
