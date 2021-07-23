package com.example.car_dealer.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class CustomersTotalSalesDto {
    @Expose
    @SerializedName(value = "fullName")
    private String name;
    @Expose
    @SerializedName(value = "boughtCars")
    private int cars;
    @Expose
    @SerializedName(value = "spentMoney")
    private String spentMoney;

    public CustomersTotalSalesDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCars() {
        return cars;
    }

    public void setCars(int cars) {
        this.cars = cars;
    }

    public String getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(String spentMoney) {
        this.spentMoney = spentMoney;
    }
}
