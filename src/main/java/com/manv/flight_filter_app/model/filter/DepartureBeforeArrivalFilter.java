package com.manv.flight_filter_app.model.filter;


import com.manv.flight_filter_app.model.Flight;
import com.manv.flight_filter_app.model.FlightFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DepartureBeforeArrivalFilter implements FlightFilter {
    public DepartureBeforeArrivalFilter() {
    }

    @Override
    public List<Flight> execute (List<Flight> flightList) {
        if (flightList == null || flightList.isEmpty()) return Collections.emptyList();
        List <Flight> resultList = new ArrayList<>();
                flightList.stream()
                .filter(f -> f.getSegments().stream()
                        .noneMatch(segment -> segment.getDepartureDate().isAfter(segment.getArrivalDate())))
                .forEach(resultList::add);
        return resultList;
    }
}
