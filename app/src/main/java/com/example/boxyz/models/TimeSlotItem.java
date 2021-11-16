package com.example.boxyz.models;

public class TimeSlotItem {

    private String day;
    private String hour;
    private String temp;

    public TimeSlotItem(String day, String hour, String temp){
        this.day = day;
        this.hour = hour;
        this.temp = temp;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    public String getTemp() {
        return temp;
    }


}
