package com.manv.flight_filter_app.telegram.service;

import com.manv.flight_filter_app.model.Flight;
import com.manv.flight_filter_app.model.FlightDTO;
import com.manv.flight_filter_app.repository.FlightRepository;
import com.manv.flight_filter_app.repository.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightBotService {
    private final FlightRepository flightRepository;
    private final SegmentRepository segmentRepository;


    @Autowired
    public FlightBotService(FlightRepository flightRepository, SegmentRepository segmentRepository) {
        this.flightRepository = flightRepository;
        this.segmentRepository = segmentRepository;

    }

    public List<FlightDTO> getAllUnfilteredFlightDTOS() {
        List<Flight> flights = flightRepository.findAll(); // Загрузка всех рейсов
        return flights.stream()
                .map(FlightDTO::fromFlight) // Преобразование в DTO
                .collect(Collectors.toList());
    }

}
