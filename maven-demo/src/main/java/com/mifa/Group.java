package com.mifa;

import java.util.ArrayList;

public class Group {
    private String name;
    private double pricePerHour;
    private ArrayList<Garage> garageList;

    public Group(){
        name = null;
        pricePerHour = -1;
    }

    public Group(String groupName, double pricePerHour){
        name = groupName;
        garageList = new ArrayList<Garage>();
        setPrice(pricePerHour);
    }

    public void registerGarage(Garage theGarage){
        garageList.add(theGarage);
        theGarage.updateGroupPrice(pricePerHour);
    }

    public void removeGarage(Garage theGarage){
        garageList.remove(theGarage);
    }

    public void notifyGarages(){
        if (!garageList.isEmpty()){
            for (int i = 0; i < garageList.size(); i++){
                garageList.get(i).updateGroupPrice(pricePerHour);
            }
        }
    }
    public void setPrice(double price){
        pricePerHour = price;
        notifyGarages();
    }

    public void changePrice(double price){
        pricePerHour = price;
        notifyGarages();
        System.out.println(this.getName() + " has changed the parking price to " + pricePerHour + " dollars/hour");
    }

    public double getPricePerHour(){
        return pricePerHour;
    }

    public Garage getMostAvailGarage(){
        int spacesNum = garageList.get(0).getSpaceAvail();
        int index = 0;
        for (int i = 1; i < garageList.size(); i++){
            if (garageList.get(i).getSpaceAvail() > spacesNum){
                spacesNum = garageList.get(i).getSpaceAvail();
                index = i;
            }
        }
        Garage MostAvailGarage = garageList.get(index);
        return MostAvailGarage;
    }

    public boolean isFull(){
        for (int i = 0; i < garageList.size(); i++){
            if (!garageList.get(i).isFull()){
                return false;
            }
        }
        return true;
    }

    public String getName(){
        return name;
    }

}

