package com.manv.flight_filter_app.service;



import com.manv.flight_filter_app.model.Flight;
import com.manv.flight_filter_app.model.FlightFilter;
import com.manv.flight_filter_app.model.FlightsAndFiltersDTO;
import com.manv.flight_filter_app.model.Segment;
import com.manv.flight_filter_app.repository.FlightRepository;
import com.manv.flight_filter_app.repository.SegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class FlightService {
    private final FilterService filterService;
    private final FlightRepository flightRepository;
    private final SegmentRepository segmentRepository;

    @Autowired
    public FlightService(FilterService filterService, FlightRepository flightRepository, SegmentRepository segmentRepository) {
        this.filterService = filterService;
        this.flightRepository = flightRepository;
        this.segmentRepository = segmentRepository;
    }

    public List<Flight> executeAllFiltersFromDto(FlightsAndFiltersDTO flightsAndFiltersDTO) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return executeAllFilters(filterService.getExactFiltersClassesFromNames(flightsAndFiltersDTO.getFilterList())
                , flightsAndFiltersDTO.getFlightList());
    }

    public List<Flight> executeAllFilters(Set<FlightFilter> filterList, List<Flight> flightList) {
        if (filterList == null || filterList.isEmpty()) {
            return flightList;
        }
        for (FlightFilter filter : filterList) {
            flightList = filter.execute(flightList);
        }
        return flightList;
    }

    private Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                    "you must pass an even number of dates");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }

    public List<Flight> getAllUnfilteredFlights() {
        return flightRepository.findAll();
    }

    public Flight createFlight(Flight flight) {
        if (flight == null) {
            throw new IllegalArgumentException("flight cannot be null");
        }

        if (flight.getSegments() != null && !flight.getSegments().isEmpty()) {
            for (Segment segment : flight.getSegments()) {
                segment.setMainFlight(flight);
                segment.setFlightId(flight.getFlightId());

            }
        }
        return flightRepository.save(flight);
    }
}
