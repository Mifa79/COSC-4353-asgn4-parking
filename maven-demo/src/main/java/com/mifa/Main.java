package com.mifa;

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.time.LocalTime;


public class Main {
    public static void main (String[] args){
        String line;
        Scanner fileIn = null;
        String fileName = null;

        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the text file name you want to test (just 1 or 2/3/4/5): ");
            fileName = keyboard.next() + ".txt";

        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
        }


        try {
            fileIn = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Test file could not be found or opened.");
            System.exit(0);
        }

        GroupCollection allGroup = new GroupCollection();

        while (fileIn.hasNextLine()) {
            line = fileIn.nextLine();
            String Words[] = line.split(" ");

            if (line.contains("Group_info:")){
                String groupName = Words[1];
                double groupPrice = Double.valueOf(Words[2]);
                Group group = new Group(groupName, groupPrice);

                for (int i = 3; i < Words.length; i++){
                    String garageInfo = Words[i];
                    String garageInfoComponent[] = garageInfo.split("#");
                    String garageName = garageInfoComponent[0];
                    int capacity = Integer.valueOf(garageInfoComponent[1]);
                    Garage garage = new Garage(garageName, capacity, group, allGroup);
                    group.registerGarage(garage);
                }

                allGroup.addGroup(group);
            }

            if (line.contains("inquiry_arrive:")){
                String arriveTimeStr = Words[0];
                String arriveTimeComponent[] = arriveTimeStr.split(":");
                int arriveHour = Integer.valueOf(arriveTimeComponent[0]);
                int arriveMin = Integer.valueOf(arriveTimeComponent[1]);
                LocalTime arriveTime = LocalTime.of(arriveHour, arriveMin);

                int index = 2;

                for (int i = 2; i < Words.length; i++) {
                    if (!allGroup.isFull()){
                        String carName = Words[i];
                        Car theCar = new Car(carName, arriveTime);
                        allGroup.sendInfoToCar(theCar);
                        Garage theChosenGarage = theCar.getChosenGarage();
                        theChosenGarage.admitCar(theCar);
                        index = index + 1;
                    } else {
                        break;
                    }
                }

                if (index <= Words.length-1){
                    for (int i = index; i < Words.length; i++) {
                        String carName = Words[i];
                        WaitCar theCar = new WaitCar(carName, arriveTime);
                        allGroup.addWaitCar(theCar);
                    }
                }
            }

            if (line.contains("exit:")){
                String exitTimeStr = Words[0];
                String exitTimeComponent[] = exitTimeStr.split(":");
                int exitHour = Integer.valueOf(exitTimeComponent[0]);
                int exitMin = Integer.valueOf(exitTimeComponent[1]);
                LocalTime exitTime = LocalTime.of(exitHour, exitMin);

                for (int i = 2; i < Words.length; i++) {
                    String carName = Words[i];
                    Car theCar = allGroup.getCarObj(carName);
                    Garage theChosenGarage = theCar.getChosenGarage();
                    theChosenGarage.exitCar(carName, exitTime);
                }
            }

            if (line.contains("Group_reset_info:")){
                String groupName = Words[1];
                double newPrice = Double.valueOf(Words[2]);
                for (int i = 0; i < allGroup.getGroupList().size(); i++){
                    if (allGroup.getGroupList().get(i).getName().equals(groupName)){
                        allGroup.getGroupList().get(i).changePrice(newPrice);
                    }
                }
            }

        }
    }
}

