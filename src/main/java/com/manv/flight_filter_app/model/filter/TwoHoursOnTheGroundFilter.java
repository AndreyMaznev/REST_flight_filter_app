package com.manv.flight_filter_app.model.filter;


import com.manv.flight_filter_app.model.Flight;
import com.manv.flight_filter_app.model.FlightFilter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class TwoHoursOnTheGroundFilter implements FlightFilter {

    public TwoHoursOnTheGroundFilter() {
    }

    @Override
    public List<Flight> execute (List<Flight> flightList) {
        if (flightList == null || flightList.isEmpty()) return Collections.emptyList();
        List <Flight> resultList = new ArrayList<>();
        flightList.stream()
                .filter(flight -> flight.getSegments().size() <= 1)
                .forEach(resultList::add);
                flightList.stream()
                .filter(flight -> flight.getSegments().size() > 1)
                .filter(flight -> {
                            long hoursOnTheGround = IntStream.range(0, flight.getSegments().size() - 1)
                                    .mapToLong(i -> Math.abs(Duration.between(flight.getSegments().get(i).getArrivalDate(),
                                            flight.getSegments().get(i+1).getDepartureDate()).toHours()))
                                    .sum();
                            return hoursOnTheGround <= 2;
                        })
                        .forEach(resultList::add);
        return resultList;
    }
}
