package com.example.car_dealer.domain.dtos;

import com.google.gson.annotations.Expose;

public class SaleDto {
    @Expose
    private String id;

    public SaleDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
