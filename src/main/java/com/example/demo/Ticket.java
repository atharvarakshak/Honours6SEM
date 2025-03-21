package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private String id;
    private String flightId;
    private String passengerName;
    private String email;
    private boolean isCancelled;
}
