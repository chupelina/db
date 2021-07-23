package com.example.car_dealer.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.swing.text.Position;
import java.math.BigDecimal;

public class PartsForCarsDto {
    @Expose
    @SerializedName(value = "Name")
    private String name;
    @Expose
    @SerializedName(value = "Price")
    private BigDecimal price;

    public PartsForCarsDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
