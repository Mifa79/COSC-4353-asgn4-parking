package com.mifa;

import java.util.ArrayList;

public class GroupCollection {
    private ArrayList<Group> groupList;
    private ArrayList<Car> carList;
    private ArrayList<WaitCar> waitCarList;

    public GroupCollection(){
        groupList = new ArrayList<Group>();
        waitCarList = new ArrayList<WaitCar>();
        carList = new ArrayList<Car>();
    }

    public void addGroup(Group theGroup){
        groupList.add(theGroup);
    }

    public Garage findAvailLowestPrice(){
        ArrayList<Group> notFullGroup = new ArrayList<Group>();
        for (int i = 0; i < groupList.size(); i++){
            if (!groupList.get(i).isFull()){
                notFullGroup.add(groupList.get(i));
            }
        }

        int index = 0;
        if (!notFullGroup.isEmpty()){
            double lowestPrice = notFullGroup.get(0).getPricePerHour();
            for (int i = 1; i < notFullGroup.size(); i++){
                if (notFullGroup.get(i).getPricePerHour() < lowestPrice){
                    lowestPrice = notFullGroup.get(i).getPricePerHour();
                    index = i;
                }
            }
        }

        Group lowestPriceGroupAvail = notFullGroup.get(index);
        Garage mostAvailSpaceGarage = lowestPriceGroupAvail.getMostAvailGarage();
        return mostAvailSpaceGarage;
    }

    public void sendInfoToCar(Car theCar){
        Garage AvailLowestPriceGarage = findAvailLowestPrice();
        Group LowestPriceGroup = AvailLowestPriceGarage.getGroup();
        double lowestPrice = LowestPriceGroup.getPricePerHour();
        int mostAvailSpace = AvailLowestPriceGarage.getSpaceAvail();
        theCar.getDataFromGroup(LowestPriceGroup, AvailLowestPriceGarage, lowestPrice);
        System.out.println(theCar.getName() + " gets informed by all groups that " + AvailLowestPriceGarage.getName() + " has the lowest price of " +
                lowestPrice +" dollars/hour and has the most available spaces (" + mostAvailSpace + " spots)");
    }

    public boolean isFull(){
        for (int i = 0; i < groupList.size(); i++){
            if (!groupList.get(i).isFull()){
                return false;
            }
        }
        return true;
    }

    public void addWaitCar(WaitCar theCar){
        waitCarList.add(theCar);
        System.out.println("All parking garages are full, " + theCar.getCarName() + " has inquired information at " + theCar.getArriveTime() +
                " and is now waiting until spot is available.");
    }

    public void addCar(Car theCar){
        carList.add(theCar);
    }

    public Car getCarObj(String carName){
        Car theCar = null;
        for (int i = 0; i < carList.size(); i++){
            if (carList.get(i).getName().equals(carName)){
                theCar = carList.get(i);
                break;
            }
        }
        return theCar;
    }

    public ArrayList<WaitCar> getWaitCarList(){
        return waitCarList;
    }

    public ArrayList<Car> getCarList(){
        return carList;
    }

    public ArrayList<Group> getGroupList(){
        return groupList;
    }
}

