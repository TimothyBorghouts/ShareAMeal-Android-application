package com.timothyborghouts.shareameal.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Meal implements Serializable {
    private String name;
    private String description;
    private boolean isActive;
    private boolean isVega;
    private boolean isVegan;
    private boolean isToTakeHome;
    private String dateTime;
    private int maxAmountOfParticipants;
    private String price;
    private String imageUrl;
    private ArrayList<String> allergies = new ArrayList<>();

    public Meal(String name, String description, boolean isActive, boolean isVega, boolean isVegan, boolean isToTakeHome, String dateTime, int maxAmountOfParticipants, String price, String imageUrl, ArrayList<String> allergies) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.isVega = isVega;
        this.isVegan = isVegan;
        this.isToTakeHome = isToTakeHome;
        this.dateTime = dateTime;
        this.maxAmountOfParticipants = maxAmountOfParticipants;
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

    public boolean isActive() { return isActive; }

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

    public int getMaxAmountOfParticipants() {
        return maxAmountOfParticipants;
    }

    public String getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArrayList<String> getAllergies() {
        return allergies;
    }

}
