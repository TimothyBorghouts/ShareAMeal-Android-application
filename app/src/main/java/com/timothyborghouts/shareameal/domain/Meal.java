package com.timothyborghouts.shareameal.domain;

import java.io.Serializable;

public class Meal implements Serializable {
    private String name;
    private String description;
    private boolean isActive;
    private boolean isVega;
    private boolean isVegan;
    private boolean isToTakeHome;
    private String dateTime;
    private int maxAmountParticipants;
    private String price;
    private String imageUrl;
    private String[] allergies;

    public Meal(String name, String description, boolean isActive, boolean isVega, boolean isVegan, boolean isToTakeHome, String dateTime, int maxAmountParticipants, String price, String imageUrl, String[] allergies) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.isVega = isVega;
        this.isVegan = isVegan;
        this.isToTakeHome = isToTakeHome;
        this.dateTime = dateTime;
        this.maxAmountParticipants = maxAmountParticipants;
        this.price = price;
        this.imageUrl = imageUrl;
        this.allergies = allergies;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isVega() {
        return isVega;
    }

    public boolean isVegan() {
        return isVegan;
    }

    public boolean isToTakeHome() {
        return isToTakeHome;
    }

    public String getDate() {
        return dateTime;
    }

    public int getMaxAmountParticipants() {
        return maxAmountParticipants;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String[] getAllergies() {
        return allergies;
    }

}
