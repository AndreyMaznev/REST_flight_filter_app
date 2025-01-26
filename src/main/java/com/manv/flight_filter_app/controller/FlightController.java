package com.manv.flight_filter_app.controller;

import com.manv.flight_filter_app.model.Flight;
import com.manv.flight_filter_app.model.FlightsAndFiltersDTO;
import com.manv.flight_filter_app.service.FilterService;
import com.manv.flight_filter_app.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Set;

@RestController
@RequestMapping ("/api/v1/flight")
public class FlightController {


    private final FlightService flightService;
    private final FilterService filterService;

    @Autowired
    public FlightController(FlightService flightService, FilterService filterService) {
        this.flightService = flightService;
        this.filterService = filterService;
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
}
