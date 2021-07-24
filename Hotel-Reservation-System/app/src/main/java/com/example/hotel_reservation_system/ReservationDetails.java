package com.example.hotel_reservation_system;

import java.util.HashSet;
import java.util.Set;

public class ReservationDetails {

    private int confirmation_id;
    private String hotel_name;
    private String checkin;
    private String checkout;
    private Set<Guest> guests_list = new HashSet<>();


    public int getConfirmation_id() {
        return confirmation_id;
    }

    public void setConfirmation_id(int confirmation_id) {
        this.confirmation_id = confirmation_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public Set<Guest> getGuests_list() {
        return guests_list;
    }

    public void setGuests_list(Set<Guest> guests_list) {
        this.guests_list = guests_list;
    }

    public void addGuest(Guest guest) {
        this.guests_list.add(guest);
    }

    public ReservationDetails(String hotel_name, String checkin, String checkout) {
        this.hotel_name = hotel_name;
        this.checkin = checkin;
        this.checkout = checkout;
    }
}
