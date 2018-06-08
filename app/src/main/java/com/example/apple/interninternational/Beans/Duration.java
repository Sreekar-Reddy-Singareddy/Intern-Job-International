package com.example.apple.interninternational.Beans;

public class Duration {

    private String duration;
    private int count;

    public Duration(String duration, int count) {
        this.duration = duration;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
