package com.example.car_dealer.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarForSalesDto {
    @Expose
    @SerializedName(value = "Make")
    private String make;
    @Expose
    @SerializedName(value = "Model")
    private String model;
    @Expose
    @SerializedName(value = "TravelledDistance")
    private long travelledDistance;

    public CarForSalesDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
