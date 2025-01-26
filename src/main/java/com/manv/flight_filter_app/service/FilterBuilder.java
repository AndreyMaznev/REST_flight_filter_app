package com.manv.flight_filter_app.service;

import com.manv.flight_filter_app.model.filter.DepartureBeforeArrivalFilter;
import com.manv.flight_filter_app.model.filter.DepartureBeforeCurrentTimeFilter;
import com.manv.flight_filter_app.model.FlightFilter;
import com.manv.flight_filter_app.model.filter.TwoHoursOnTheGroundFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterBuilder {


    public static List<FlightFilter> createFilterList (List <String> filtersClassNames) {
        List <FlightFilter> result = new ArrayList<>();


        //Add reflection to automatically create filter list.
        for (String filterName : filtersClassNames) {
            switch (filterName) {
                case "DepartureBeforeArrivalFilter" :
                    result.add(new DepartureBeforeArrivalFilter());
                    break;
                case "DepartureBeforeCurrentTimeFilter":
                    result.add(new DepartureBeforeCurrentTimeFilter());
                    break;
                case "TwoHoursOnTheGroundFilter":
                    result.add(new TwoHoursOnTheGroundFilter());
                    break;
            }
        }
        return result;
    }
}
