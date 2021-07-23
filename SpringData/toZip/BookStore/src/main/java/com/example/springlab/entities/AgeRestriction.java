package com.example.springlab.entities;

public enum AgeRestriction {
    MINOR(0),
    TEEN(1),
    ADULT(2);
    private int position;

    AgeRestriction(int position) {
      this.position=position;
    }


    public static String getPosition(int n) {
       if(n==0){
           return MINOR.name();
       }else if(n==1){
           return TEEN.name();
       }
       return ADULT.name();
    }
}
