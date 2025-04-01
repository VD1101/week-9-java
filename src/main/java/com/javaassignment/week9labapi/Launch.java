package com.javaassignment.week9labapi;

public class Launch {
    private String name;
    private int flightNumber;
    private String dateUtc;
    private boolean success;
    private String wikipedia;
    private String imageUrl;

    public Launch(String name, int flightNumber, String dateUtc, boolean success, String wikipedia, String imageUrl) {
        this.name = name;
        this.flightNumber = flightNumber;
        this.dateUtc = dateUtc;
        this.success = success;
        this.wikipedia = wikipedia;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String getDateUtc() {
        return dateUtc;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
