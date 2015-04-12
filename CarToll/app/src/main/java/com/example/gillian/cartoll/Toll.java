package com.example.gillian.cartoll;

/**
 * Created by Gillian on 12/04/2015.
 */

enum VehicleType{
    Car, Truck, Bus
}

public class Toll {

    private double cost;
    public double getCost(){
        return cost;
    };

    private boolean hasTag;
    public boolean getHasTag(){
        return hasTag;
    };

    private VehicleType vehicleType;
    public VehicleType getVehicleType(){
        return vehicleType;
    };

    public Toll(VehicleType vehicleType){
        this.vehicleType=vehicleType;
    }

    public Toll(boolean hasTag, VehicleType vehicleType){
        if(vehicleType.equals(vehicleType.Car)){
            cost = 1.50;
        }
        else if(vehicleType.equals(vehicleType.Bus)){
            cost = 2.00;
        }
        else if(vehicleType.equals(vehicleType.Truck)){
            cost = 2.50;
        }
        this.hasTag = hasTag;
        this.vehicleType = vehicleType;
    }
}
