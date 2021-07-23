package com.example.car_dealer.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.Set;

public class CustomerDto {
    @Expose
    @SerializedName(value = "Id")
    private long id;
    @Expose
    @SerializedName(value = "Name")
    private String name;
    @Expose
    @SerializedName(value = "BirthDate")
    private String birthDate;
    @Expose
    @SerializedName(value = "IsYoungDriver")
    private boolean isYoungDriver;
    @Expose
    @SerializedName(value = "Sales")
    private Set<SaleDto> sales;

    public CustomerDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleDto> getSales() {
        return sales;
    }

    public void setSales(Set<SaleDto> sales) {
        this.sales = sales;
    }
}
