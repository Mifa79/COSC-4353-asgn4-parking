package com.mifa;

import java.time.LocalTime;

public class WaitCar {
    private String name;
    private LocalTime arriveTime;

    public WaitCar(){
        name = null;
        arriveTime = null;
    }

    public WaitCar(String car_name, LocalTime the_arriveTime){
        name = car_name;
        arriveTime = the_arriveTime;
    }

    public String getCarName(){
        return name;
    }

    public LocalTime getArriveTime(){
        return arriveTime;
    }

}

