package com.manv.flight_filter_app.model.filter;


import com.manv.flight_filter_app.model.Flight;
import com.manv.flight_filter_app.model.FlightFilter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;


public class DepartureBeforeCurrentTimeFilter implements FlightFilter {
    private final LocalDateTime currentTime;

    public DepartureBeforeCurrentTimeFilter(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }
    public DepartureBeforeCurrentTimeFilter() {
        this.currentTime = LocalDateTime.now();
    }

    @Override
    public List<Flight> execute (List<Flight> flightList) {
        if (flightList == null || flightList.isEmpty()) return Collections.emptyList();
        return flightList.stream()
                .filter(f -> f.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(currentTime)))
                .collect(toList());
    }
}
