package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String id;
    private String airline;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private List<String> schedules;
}
