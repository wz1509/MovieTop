package me.jokey.movie.model;

/**
 * Created by wz on 2017/9/7 15:43.
 * desc:评分实体类
 */
public class RatingEntity {

    private int max;
    private float average;
    private String stars;
    private String min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
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
