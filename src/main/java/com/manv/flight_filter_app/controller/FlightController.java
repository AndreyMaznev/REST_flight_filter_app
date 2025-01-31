package com.manv.flight_filter_app.controller;

import com.manv.flight_filter_app.model.Flight;
import com.manv.flight_filter_app.model.FlightsAndFiltersDTO;
import com.manv.flight_filter_app.model.Segment;
import com.manv.flight_filter_app.repository.FlightRepository;
import com.manv.flight_filter_app.service.FilterService;
import com.manv.flight_filter_app.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping ("/api/v1/flights")
public class FlightController {


    private final FlightService flightService;
    private final FilterService filterService;
    private final FlightRepository flightRepository;

    @Autowired
    public FlightController(FlightService flightService, FilterService filterService, FlightRepository flightRepository) {
        this.flightService = flightService;
        this.filterService = filterService;
        this.flightRepository = flightRepository;
    }


    @GetMapping
    public List<Flight> getAllFlights(
            @RequestParam(value = "departureDateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateFrom,
            @RequestParam(value = "departureDateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTo,
            @RequestParam(value = "arrivalDateFrom", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalDateFrom,
            @RequestParam(value = "arrivalDateTo", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalDateTo
    ) {
        return flightService.findBySegmentDatesBetween(departureDateFrom, departureDateTo, arrivalDateFrom, arrivalDateTo);
    }

    @PostMapping("/create")
    public ResponseEntity <Flight> createFlight (@RequestBody Flight flight) {
        return new ResponseEntity<>(flightService.createFlight(flight), HttpStatus.CREATED);
    }

    //todo RestAdvise
    //todo exceptions
    @PostMapping("/filter")
    public ResponseEntity <List<Flight>> getFilteredFlightList (@RequestBody FlightsAndFiltersDTO flightsAndFiltersDTO) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return new ResponseEntity<>(
                flightService.executeAllFiltersFromDto(flightsAndFiltersDTO),
                HttpStatus.OK);
    }

    @GetMapping ("/getAvailableFilterList")
    public ResponseEntity <Set <String>> getAvailableFilters () {
        return new ResponseEntity<>(filterService.getAvailableFilters(), HttpStatus.OK);
    }

    @GetMapping("/segments/{flightId}")
    public List <Segment> getSegmentsByFlightId(@PathVariable UUID flightId) {
        return flightService.findByMainFlightFlightId(flightId);
    }
}
