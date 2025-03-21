package com.example.demo.service;

import com.example.demo.model.Flight;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {
    private final List<Flight> flights = new ArrayList<>();

    public FlightService() {
        flights.add(new Flight("1", "Indigo", "Delhi", "Mumbai", "08:00 AM", "10:00 AM", List.of("2024-03-25", "2024-03-26")));
        flights.add(new Flight("2", "Air India", "Mumbai", "Bangalore", "02:00 PM", "04:00 PM", List.of("2024-03-25", "2024-03-26")));
    }

    public List<Flight> getAllFlights() {
        return flights;
    }

    public Flight getFlightById(String id) {
        return flights.stream().filter(f -> f.getId().equals(id)).findFirst().orElse(null);
    }

    public List<String> getFlightSchedules(String id) {
        Flight flight = getFlightById(id);
        return (flight != null) ? flight.getSchedules() : null;
    }
}
