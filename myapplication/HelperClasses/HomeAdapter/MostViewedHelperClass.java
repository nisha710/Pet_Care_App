package com.example.myapplication.HelperClasses.HomeAdapter;

public class MostViewedHelperClass {
    int image;
    String title,description;

    public MostViewedHelperClass(int image, String title, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
