package com.manv.flight_filter_app.service;

import com.manv.flight_filter_app.model.Flight;
import com.manv.flight_filter_app.model.FlightDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FlightMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public FlightMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FlightDTO toDto (Flight flight) {
        return Objects.isNull(flight) ? null : modelMapper.map(flight, FlightDTO.class);
    }

    public Flight toEntity (FlightDTO flightDTO) {
        return Objects.isNull(flightDTO) ? null : modelMapper.map(flightDTO, Flight.class);
    }
}