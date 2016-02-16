package b1ackr0se.io.movieholic.data.model;

public class Video {
    public static final String URL_YOUTUBE = "YouTube";
    public static final String EMPTY = "";

    private String id;
    private String name;
    private String site;

    public static String getVideoUrl(Video video) {
        if(video.getSite().equalsIgnoreCase(URL_YOUTUBE))
            return String.format("http://www.youtube.com/watch?v=%1$s", video.getId());
        else return EMPTY;

    }

    public static String getThumbnail(Video video) {
        if(video.getSite().equalsIgnoreCase(URL_YOUTUBE))
            return String.format("http://img.youtube.com/vi/%1$s/0.jpg", video.getId());
        else
            return EMPTY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

}
