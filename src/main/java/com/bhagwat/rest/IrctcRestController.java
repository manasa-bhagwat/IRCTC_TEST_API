package com.bhagwat.rest;

import com.bhagwat.request.Passenger;
import com.bhagwat.response.Ticket;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
public class IrctcRestController {

    public int ticketId = 101;

    private Map<Integer, Ticket> tickets = new HashMap<>();

    @PostMapping(
            value = "/book/ticket",
            consumes = {"application/xml","application/json"},
            produces = {"application/xml","application/json"}
    )
    public Ticket bookTicket(@RequestBody Passenger passenger) {

        System.out.println("Received Passenger details to Control Layer." + passenger);
        // logic to book ticket
        Ticket ticket = new Ticket();

        Random r = new Random();
        ticket.setTicketId(ticketId);

        ticket.setFrom(passenger.getFrom());
        ticket.setTo(passenger.getTo());
        ticket.setTicketStatus("CONFIRMED");
        ticket.setTrainNum(passenger.getTrainNum());
        ticket.setTktCost("1500.00 INR");

        tickets.put(ticketId, ticket);
        ticketId++;

        return ticket;
    }

    @GetMapping(
            value = "/get/ticket/{ticketId}",
            produces = {"application/xml","application/json"}
    )
    public Ticket getTicket(@PathVariable Integer ticketId) {
        System.out.println(tickets);

        if (tickets.containsKey(ticketId)) return tickets.get(ticketId);
        return null;
    }
}
