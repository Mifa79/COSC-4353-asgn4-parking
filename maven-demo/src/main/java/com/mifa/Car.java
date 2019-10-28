package com.mifa;

import java.time.LocalTime;

public class Car {
    private String name;
    private LocalTime enter_time;
    private Group chosenGroup;
    private Garage chosenGarage;
    private double pricePerHour;


    public Car(){
        name = null;
        enter_time = null;
        pricePerHour = -1;
    }

    public Car(String car_name, LocalTime the_enter_time){
        name = car_name;
        enter_time = the_enter_time;
    }

    public void setEnter_time(LocalTime the_time) {
        enter_time = the_time;
    }

    public String getName(){
        return name;
    }

    public LocalTime getEnter_time(){
        return enter_time;
    }

    public void setParkingPrice(double thePrice){
        pricePerHour = thePrice;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void getDataFromGroup(Group theChosenGroup, Garage theChosenGarage, double price){
        chosenGroup = theChosenGroup;
        chosenGarage = theChosenGarage;
        pricePerHour = price;
    }

    public Garage getChosenGarage(){
        return chosenGarage;
    }
}

