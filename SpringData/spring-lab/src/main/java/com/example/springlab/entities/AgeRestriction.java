package com.example.springlab.entities;

public enum AgeRestriction {
    MINOR(0),
    TEEN(1),
    ADULT(2);
    private int position;

    AgeRestriction(int position) {
      this.position=position;
    }

}
