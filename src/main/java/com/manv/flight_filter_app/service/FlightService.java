package com.manv.flight_filter_app.service;



import com.manv.flight_filter_app.model.Flight;
import com.manv.flight_filter_app.model.FlightFilter;
import com.manv.flight_filter_app.model.FlightsAndFiltersDTO;
import com.manv.flight_filter_app.model.Segment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class FlightService {
    private final FilterService filterService;

    @Autowired
    public FlightService(FilterService filterService) {
        this.filterService = filterService;
    }

    public List <Flight> executeAllFiltersFromDto (FlightsAndFiltersDTO flightsAndFiltersDTO) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return executeAllFilters(filterService.getExactFiltersClassesFromNames(flightsAndFiltersDTO.getFilterList())
                ,flightsAndFiltersDTO.getFlightList());
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

}
