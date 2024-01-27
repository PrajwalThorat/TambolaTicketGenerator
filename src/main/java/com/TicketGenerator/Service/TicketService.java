package com.TicketGenerator.Service;


import com.TicketGenerator.Entity.Ticket;
import com.TicketGenerator.Repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TicketService {

    private static final int ROWS = 3;
    private static final int COLUMNS = 9;
    private static final int NUMBERS_PER_ROW = 5;
    private static final int TOTAL_NUMBERS = ROWS * COLUMNS;

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket generateTicket(int ticketNumber) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= TOTAL_NUMBERS; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        Ticket ticket = new Ticket();
        int[][] ticketNumbers = new int[ROWS][COLUMNS];

        int index = 0;
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS; row++) {
                ticketNumbers[row][col] = numbers.get(index++);
            }
        }

        ticket.setTicketNumber(ticketNumber);
        ticket.setNumbers(ticketNumbers);
        return ticketRepository.save(ticket);
    }

    public List<Ticket> generateNTickets(int n) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tickets.add(generateTicket(i + 1));
        }
        return tickets;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
    public Page<Ticket> getAllTickets(Pageable pageable) {
        return ticketRepository.findAll(pageable);
    }
}
