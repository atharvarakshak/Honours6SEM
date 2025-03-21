package com.example.demo.service;

import com.example.demo.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final List<Ticket> tickets = new ArrayList<>();

    public List<Ticket> getAllTickets() {
        return tickets;
    }

    public Ticket getTicketById(String id) {
        return tickets.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public Ticket createTicket(Ticket ticket) {
        tickets.add(ticket);
        return ticket;
    }

    public boolean cancelTicket(String id) {
        Optional<Ticket> ticket = tickets.stream().filter(t -> t.getId().equals(id)).findFirst();
        ticket.ifPresent(t -> t.setCancelled(true));
        return ticket.isPresent();
    }
}
