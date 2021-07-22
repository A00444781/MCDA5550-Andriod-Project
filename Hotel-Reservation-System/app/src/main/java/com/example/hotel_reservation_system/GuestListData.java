package com.example.hotel_reservation_system;

//create data model for hotel list recycler view
public class GuestListData {

    String guest_name;
    boolean gender;
    int guestId;

    public GuestListData(String guest_name, boolean gender) {
        this.guest_name = guest_name;
        this.gender = gender;
    }

    public String getGuest_name() {
        return guest_name;
    }

//    public void setGuest_name(String guest_name) {
//        this.guest_name = guest_name;
//    }

    public boolean isGender() {
        return gender;
    }

//    public void setGender(boolean gender) {
//        this.gender = gender;
//    }

    public int getGuestId() {
        return guestId;
    }
}
