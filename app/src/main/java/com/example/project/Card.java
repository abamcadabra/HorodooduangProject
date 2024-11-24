package com.example.project;
public class Card {
    private int id;
    private int imageId;
    private String name;
    private String description;
    private String advice;

    public Card(int id, int imageId, String name, String description, String advice) {
        this.id = id;
        this.imageId = imageId;
        this.name = name;
        this.description = description;
        this.advice = advice;
    }

    public int getId() {
        return id;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAdvice() {
        return advice;
    }
}

