package me.jokey.movie.model;

/**
 * Created by wz on 2017/9/7 15:43.
 * desc:评分实体类
 */
public class RatingEntity {

    private String max;
    private String average;
    private String stars;
    private String min;

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "RatingEntity{" +
                "max='" + max + '\'' +
                ", average='" + average + '\'' +
                ", stars='" + stars + '\'' +
                ", min='" + min + '\'' +
                '}';
    }
}
