package com.TicketGenerator.Entity;


import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.IOException;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ticketNumber;

    @JsonRawValue
    @JsonSerialize(using = TicketNumbersSerializer.class)
    private String numbers;

    public int[][] getNumbers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(numbers, int[][].class);
        } catch (IOException e) {
            throw new RuntimeException("Error reading numbers from JSON", e);
        }
    }

    public void setNumbers(int[][] numbers) {
        try {
            this.numbers = new ObjectMapper().writeValueAsString(numbers);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error writing numbers to JSON", e);
        }
    }
}
