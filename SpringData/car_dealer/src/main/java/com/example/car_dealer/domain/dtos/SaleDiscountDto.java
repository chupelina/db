package com.example.car_dealer.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class SaleDiscountDto {
    @Expose
    @SerializedName(value = "car")
    private CarForSalesDto car;
    @Expose
    @SerializedName(value = "customerName")
    private String name;
    @Expose
    @SerializedName(value = "Discount")
    private double discount;
    @Expose
    @SerializedName(value = "price")
    private BigDecimal price;
    @Expose
    @SerializedName(value = "priceWithDiscount")
    private BigDecimal priceWithDiscount;

    public SaleDiscountDto() {
    }

    public CarForSalesDto getCar() {
        return car;
    }

    public void setCar(CarForSalesDto car) {
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
