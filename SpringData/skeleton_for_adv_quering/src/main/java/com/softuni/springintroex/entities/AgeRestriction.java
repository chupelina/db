package com.softuni.springintroex.entities;

public enum  AgeRestriction {
    MINOR, TEEN , ADULT;

    public static boolean isContaining(String name){
       if( name.toUpperCase().equals("MINOR") || name.toUpperCase().equals("ADULT")
               ||name.toUpperCase().equals("TEEN")){
           return true;
       }
       return false;
     }
}
