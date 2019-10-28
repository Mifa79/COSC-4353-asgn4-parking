package com.mifa;

import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.ArrayList;

public class Garage {
    private String name;
    private Group group;
    private GroupCollection groupCollection;
    private double pricePerHour;
    private int capacity;
    private ArrayList<Car> carList;


    public Garage(){
        group = null;
        capacity = -1;
        pricePerHour = -1;
    }

    public Garage(String theName, int the_capacity, Group theGroup, GroupCollection theGroupCollection){
        name = theName;
        capacity = the_capacity;
        group = theGroup;
        groupCollection = theGroupCollection;
        group.registerGarage(this);
        carList = new ArrayList<Car>();
    }

    public void updateGroupPrice(double thePricePerHour){
        pricePerHour = thePricePerHour;
    }

    public void admitCar(Car the_car){
        the_car.setParkingPrice(pricePerHour);
        carList.add(the_car);
        groupCollection.addCar(the_car);
        System.out.println(the_car.getName() + " gets a " + the_car.getEnter_time() + " time-stamped ticket and enters " + this.name);
    }

    public void exitCar(String the_car, LocalTime the_Exit_Time){
        billCar(the_car, the_Exit_Time);

        for (int i = 0; i < carList.size(); i++){
            if (carList.get(i).getName().equals(the_car)){
                carList.remove(carList.get(i));
//                System.out.println("size: " + carList.size());
            }
        }

        for (int i = 0; i < groupCollection.getCarList().size(); i++){
            if (groupCollection.getCarList().get(i).getName().equals(the_car)){
                groupCollection.getCarList().remove(groupCollection.getCarList().get(i));
            }
        }

        ArrayList<WaitCar> waitCarList = groupCollection.getWaitCarList();

        if (waitCarList.size() != 0){
            int index = 0;
            LocalTime temp = waitCarList.get(0).getArriveTime();
            for (int i = 1; i < waitCarList.size(); i++){
                if (waitCarList.get(i).getArriveTime().isBefore(temp)){
                    temp = waitCarList.get(i).getArriveTime();
                    index = i;
                }
            }

            Car nowEnterCar = new Car(waitCarList.get(index).getCarName(), the_Exit_Time);
            groupCollection.sendInfoToCar(nowEnterCar);
            Garage theChosenGarage = nowEnterCar.getChosenGarage();
            waitCarList.remove(waitCarList.get(index));
            theChosenGarage.admitCar(nowEnterCar);
        }
    }

    public boolean isFull(){
        if (carList.size() == capacity){
            return true;
        } else {
            return false;
        }
    }


    public void billCar(String the_car, LocalTime the_ExitTime){
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getName().equals(the_car)) {
                LocalTime enterTime = carList.get(i).getEnter_time();
                Long totalMin = enterTime.until(the_ExitTime, MINUTES);
                double totalHour = totalMin/60.0000;
                String totalHourStr = String.format("%.2f", totalHour);
                double price = carList.get(i).getPricePerHour();
                double payAmount = totalHour * price;
                String payAmountStr = String.format("%.2f", payAmount);
                System.out.println("At " + the_ExitTime + ", at " + this.name + ", " + the_car +  " presents their ticket and pays " + payAmountStr +
                        " dollars for " + totalHourStr + " hours parking. " + the_car + " exits from " + this.name);
            }
        }
    }


    public int getSpaceAvail(){
        int spaceAvail = capacity - carList.size();
        return spaceAvail;
    }

    public Group getGroup(){
        return group;
    }

    public String getName(){
        return name;
    }

}

