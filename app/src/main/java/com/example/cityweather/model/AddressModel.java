package com.example.cityweather.model;

public class AddressModel {
    String cityName="";
    String geonameId="";
    double latitude;
    double longtitude;
    int population = 0;

    public AddressModel(String cityName, String geonameId, double latitude, double longtitude, int population) {
        this.cityName = cityName;
        this.geonameId = geonameId;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.population = population;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setGeonameId(String geonameId) {
        this.geonameId = geonameId;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCityName() {
        return cityName;
    }

    public String getGeonameId() {
        return geonameId;
    }

    public String getLatitude() {
        return String.valueOf(latitude);
    }

    public String getLongtitude() {
        return String.valueOf(longtitude);
    }

    public String getPopulation() {
        return String.valueOf(population);
    }
}
