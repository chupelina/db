package com.example.car_dealer.domain.dtos;

import com.example.car_dealer.domain.entities.Part;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Set;
public class CarsDto {
    @Expose
    @SerializedName(value = "Make")
    private String make;
    @Expose
    @SerializedName(value = "Model")
    private String model;
    @Expose
    @SerializedName(value = "TravelledDistance")
    private long travelledDistance;
    @Expose
    @SerializedName(value = "parts")
    private Set<PartsForCarsDto> parts;

    public CarsDto() {
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

    public Set<PartsForCarsDto> getParts() {
        return parts;
    }

    public void setParts(Set<PartsForCarsDto> parts) {
        this.parts = parts;
    }
}
