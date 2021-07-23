package com.example.car_dealer.domain.dtos;

import com.example.car_dealer.domain.entities.Part;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class LocalSupplierDto {
    @Expose
    @SerializedName(value= "Id")
    private long id;
    @Expose
    @SerializedName(value = "Name")
    private String name;
    @Expose
    @SerializedName(value = "partsCount")
    private int parts;

    public LocalSupplierDto() {
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

    public int getParts() {
        return parts;
    }

    public void setParts(int parts) {
        this.parts = parts;
    }
}
