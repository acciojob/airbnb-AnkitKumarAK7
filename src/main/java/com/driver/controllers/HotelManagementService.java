package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelManagementService {
    
    @Autowired
    static
    HotelManagementRepository hotelManagementRepository;

    public void addHotel(Hotel hotel) {
         hotelManagementRepository.addHotel(hotel);
    }

    public Integer addUser(User user) {
        return hotelManagementRepository.addUser(user);
    }

    public static String getHotelWithMostFacilities() {
        return hotelManagementRepository.getHotelWithMostFacilities();

    }
    public static int bookARoom(Booking booking) {
        return hotelManagementRepository.bookARoom(booking);
    }

    public int getBooking(Integer aadharCard) {
        return hotelManagementRepository.getBooking(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return hotelManagementRepository.updateFacilities(newFacilities, hotelName);
    }
}
