package com.example.apple.interninternational.Beans;

public class Profile {

    private int imageId;
    private String name;
    private String role;

    public Profile(int imageId, String name, String role) {
        this.imageId = imageId;
        this.name = name;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
