package com.manv.flight_filter_app.model;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class FlightsAndFiltersDTO {
    private List<Flight> flightList;
    private Set<String> filterList;

    public FlightsAndFiltersDTO() {
        flightList = new ArrayList<>();
        filterList = new HashSet<>();
    }

    public FlightsAndFiltersDTO(List<Flight> flightList, Set<String> filterList) {
        this.flightList = flightList;
        this.filterList = filterList;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public Set<String> getFilterList() {
        return filterList;
    }

    public void setFilterList(Set<String> filterList) {
        this.filterList = filterList;
    }
}
