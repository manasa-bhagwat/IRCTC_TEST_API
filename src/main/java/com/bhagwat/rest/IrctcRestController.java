package com.bhagwat.rest;

import com.bhagwat.request.Passenger;
import com.bhagwat.response.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/irctc")
public class IrctcRestController {

    private AtomicInteger ticketIdCounter = new AtomicInteger(101);
    private Map<Integer, Ticket> tickets = new HashMap<>();

    @PostMapping(value = "/book/ticket", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Ticket> bookTicket(@RequestBody Passenger passenger) {
        System.out.println("Received Passenger details: " + passenger);

        Ticket ticket = new Ticket();
        int ticketId = ticketIdCounter.getAndIncrement();

        ticket.setTicketId(ticketId);
        ticket.setFrom(passenger.getFrom());
        ticket.setTo(passenger.getTo());
        ticket.setTicketStatus("CONFIRMED");
        ticket.setTrainNum(passenger.getTrainNum());
        ticket.setTktCost("1500.00 INR");

        tickets.put(ticketId, ticket); // Store ticket in the map

        return ResponseEntity.ok(ticket);
    }

    @GetMapping(value = "/get/ticket/{ticketId}", produces = "application/json")
    public ResponseEntity<?> getTicket(@PathVariable Integer ticketId) {
        System.out.println("Current tickets: " + tickets);

        Ticket ticket = tickets.get(ticketId);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        }
        return ResponseEntity.status(404).body("Ticket Not Found");
    }
}
