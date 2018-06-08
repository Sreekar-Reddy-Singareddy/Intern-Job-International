package com.example.apple.interninternational.Beans;

public class Branch {

    private int imageId;
    private String name;
    private int count;

    public Branch(int imageId, String name, int count) {
        this.imageId = imageId;
        this.name = name;
        this.count = count;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
