package com.TicketGenerator.Entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class TicketNumbersSerializer extends JsonSerializer<int[][]> {

    @Override
    public void serialize(int[][] numbers, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        jsonGenerator.writeObject(numbers);
    }
}
