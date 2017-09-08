package me.jokey.movie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wz on 2017/9/7 16:42.
 * desc:影片详情
 */
public class MovieDetailEntity {

    private String id;

    private String title;

    @SerializedName("original_title")
    private String originalTitle;

    private String year;

    private String summary;

    @SerializedName("mobile_url")
    private String mobileUrl;

    @SerializedName("share_url")
    private String shareUrl;

    private List<String> countries;

    private List<String> genres;

    private List<String> aka;

    private RatingEntity rating;

    private ImagesEntity images;

    private List<PersonageEntity> casts;

    private List<PersonageEntity> directors;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getMobileUrl() {
        return mobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        this.mobileUrl = mobileUrl;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public RatingEntity getRating() {
        return rating;
    }

    public void setRating(RatingEntity rating) {
        this.rating = rating;
    }

    public ImagesEntity getImages() {
        return images;
    }

    public void setImages(ImagesEntity images) {
        this.images = images;
    }

    public List<PersonageEntity> getCasts() {
        return casts;
    }

    public void setCasts(List<PersonageEntity> casts) {
        this.casts = casts;
    }

    public List<PersonageEntity> getDirectors() {
        return directors;
    }

    public void setDirectors(List<PersonageEntity> directors) {
        this.directors = directors;
    }

    @Override
    public String toString() {
        return "MovieDetailEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", year='" + year + '\'' +
                ", summary='" + summary + '\'' +
                ", mobileUrl='" + mobileUrl + '\'' +
                ", shareUrl='" + shareUrl + '\'' +
                ", countries=" + countries +
                ", genres=" + genres +
                ", aka=" + aka +
                ", rating=" + rating +
                ", images=" + images +
                ", casts=" + casts +
                ", directors=" + directors +
                '}';
    }
}
