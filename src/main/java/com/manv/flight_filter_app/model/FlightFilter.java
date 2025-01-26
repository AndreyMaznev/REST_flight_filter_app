package com.manv.flight_filter_app.model;

import java.util.List;

public interface FlightFilter {
    List <Flight> execute (List<Flight> flights);
}
