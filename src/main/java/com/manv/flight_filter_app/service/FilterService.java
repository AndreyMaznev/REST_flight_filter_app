package com.manv.flight_filter_app.service;

import com.manv.flight_filter_app.model.filter.DepartureBeforeCurrentTimeFilter;
import com.manv.flight_filter_app.model.FlightFilter;
import org.reflections.Reflections;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class FilterService {
    public Set <String> getAvailableFilters () {
        Set <String> result = new HashSet<>();
        for (Class<? extends FlightFilter> filter : getAllFlightFilterClassesFromReflections()) {
            result.add(filter.getSimpleName());
        }
        return result;
    }

    public Set <Class <? extends FlightFilter>> getAllFlightFilterClassesFromReflections() {
        Reflections reflections = new Reflections("com.manv.flight_filter_app");
        return reflections.getSubTypesOf(FlightFilter.class);
    }

    public Set <FlightFilter> getExactFiltersClassesFromNames (Set <String> filters) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Set <FlightFilter> result = new HashSet<>();
        for (String filter : filters) {
            Class<?> clazz = Class.forName("com.manv.flight_filter_app.model.filter." + filter);
            FlightFilter flightFilter = (FlightFilter) clazz.newInstance();
            result.add(flightFilter);
        }
        return result;
    }
}
