package com.ekyoz.boxyz.models.timeSlotModels;

public class ItemDayModel {

    private int temp;
    private String name;
    private boolean status;


    public ItemDayModel(int temp, String name){
        this.temp = temp;
        this.name = name;
        this.status = status;
    }

    public int getTemp() {
        return temp;
    }

    public String getName() {
        return name;
    }

    public boolean getStatus() {
        return status;
    }

}
