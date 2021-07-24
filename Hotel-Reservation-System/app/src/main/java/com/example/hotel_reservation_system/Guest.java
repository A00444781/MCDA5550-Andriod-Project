package com.example.hotel_reservation_system;

public class Guest {
    private int quest_id;
    private String guest_name;
    private int gender;

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Guest(){

    }

    public Guest(String guest_name, int gender) {
        this.guest_name = guest_name;
        this.gender = gender;
    }
}
