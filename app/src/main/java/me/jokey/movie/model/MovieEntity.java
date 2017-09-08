package me.jokey.movie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wz on 2017/9/6 18:41.
 * desc:
 */
public class MovieEntity {

    private String id;

    private String title;

    private String subtype;

    @SerializedName("original_title")
    private String originalTitle;

    private String alt;

    private RatingEntity rating;

    private List<String> genres;

    private List<PersonageEntity> casts;

    private List<PersonageEntity> directors;

    private ImagesEntity images;

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

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public RatingEntity getRating() {
        return rating;
    }

    public void setRating(RatingEntity rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
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

    public ImagesEntity getImages() {
        return images;
    }

    public void setImages(ImagesEntity images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "MovieEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", subtype='" + subtype + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", alt='" + alt + '\'' +
                ", rating=" + rating +
                ", genres=" + genres +
                ", casts=" + casts +
                ", directors=" + directors +
                ", images=" + images +
                '}';
    }
}
