package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class HotelManagementRepository {
    Map<String, Hotel> hotelDb = new HashMap<>();
    Map<Integer, User> userDb = new HashMap<>();
    Map<String, Booking> bookingDb = new HashMap<>();

    public void addHotel(Hotel hotel) {
        if (hotel != null && hotel.getHotelName() != null) {
            // Check for duplicate hotel names before adding
            if (!hotelDb.containsKey(hotel.getHotelName())) {
                hotelDb.put(hotel.getHotelName(), hotel);
            }
        }
    }

    public Integer addUser(User user) {
        if (user != null) {
            userDb.put(user.getaadharCardNo(), user);
        }
        return null;
    }

    public String getHotelWithMostFacilities() {
        String hotelWithMostFacilities = "";
        int maxFacilitiesCount = 0;

        // Iterate through all hotels in your database
        for (Hotel hotel : hotelDb.values()) {
            int facilitiesCount = hotel.getFacilities().size();

            // Check if this hotel has more facilities than the current max
            if (facilitiesCount > maxFacilitiesCount) {
                maxFacilitiesCount = facilitiesCount;
                hotelWithMostFacilities = hotel.getHotelName();
            } else if (facilitiesCount == maxFacilitiesCount) {
                // If there's a tie, compare hotel names lexicographically
                hotelWithMostFacilities = hotel.getHotelName().compareTo(hotelWithMostFacilities) < 0
                        ? hotel.getHotelName()
                        : hotelWithMostFacilities;
            }
        }
        return hotelWithMostFacilities;
    }

    public int bookARoom(Booking booking) {
        // Generate a random UUID as the bookingId
        String bookingId = UUID.randomUUID().toString();
        booking.setBookingId(bookingId);

        return 0; // Cast the double to int if needed
    }

    public int getBooking(Integer aadharCard) {
        int bookingsCount = 0;

        for (Booking booking : bookingDb.values()) {
            // Check if the booking is associated with the specified aadharCard
            if (booking.getBookingAadharCard()== aadharCard) {
                bookingsCount++;
            }
        }
        return bookingsCount;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        // Get the hotel from the hotelDb based on the hotelName
        Hotel hotel = hotelDb.get(hotelName);

        if (hotel != null) {
            // Get the current facilities of the hotel
            List<Facility> currentFacilities = hotel.getFacilities();

            // Add new facilities to the hotel's facilities list if they are not already present
            for (Facility newFacility : newFacilities) {
                if (!currentFacilities.contains(newFacility)) {
                    currentFacilities.add(newFacility);
                }
            }

            // Update the hotel's facilities list in the hotelDb
            hotel.setFacilities(currentFacilities);
    }
        return hotel;
   }
 }