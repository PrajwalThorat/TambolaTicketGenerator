package com.TicketGenerator.Controller;


import com.TicketGenerator.Entity.Ticket;
import com.TicketGenerator.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public Ticket generateSingleTicket() {
        return ticketService.generateTicket(0);
    }

    @PostMapping("/generateBatch/{n}")
    public List<Ticket> generateTicketBatch(@PathVariable int n) {
        return ticketService.generateNTickets(n);
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }
    @GetMapping("/paged")
    public Page<Ticket> getAllTickets(Pageable pageable) {
        return ticketService.getAllTickets(pageable);
    }
}
