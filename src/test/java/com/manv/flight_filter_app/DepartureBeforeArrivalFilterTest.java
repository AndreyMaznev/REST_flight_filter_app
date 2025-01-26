package com.manv.flight_filter_app;


import com.manv.flight_filter_app.model.filter.DepartureBeforeArrivalFilter;
import com.manv.flight_filter_app.model.Flight;
import com.manv.flight_filter_app.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DepartureBeforeArrivalFilterTest {
    DepartureBeforeArrivalFilter filter = new DepartureBeforeArrivalFilter();

    Segment testSegmentOne = new Segment(LocalDateTime.of(2024,12,29,3,30)
            , LocalDateTime.of(2024,12,29,12,30));

    Segment testSegmentTwo = new Segment(LocalDateTime.of(2024,12,29,12,30)
            , LocalDateTime.of(2024,12,29,3,30));

    Segment testSegmentThree = new Segment(LocalDateTime.of(2024,12,30,12,30)
            , LocalDateTime.of(2024,12,28,3,30));


    Flight testFlightOne = new Flight(List.of(testSegmentOne));
    Flight testFlightTwo = new Flight(List.of(testSegmentOne, testSegmentTwo));
    Flight testFlightThree = new Flight(List.of(testSegmentOne, testSegmentTwo,testSegmentThree));

    List <Flight> expectedFilteredFlightsList  = new ArrayList<>(List.of(testFlightOne));

    @Test
    void executeReturnsRightFlightsList() {
        assertEquals (filter.execute(Arrays.asList(testFlightOne,testFlightTwo)), expectedFilteredFlightsList);
        assertEquals (filter.execute(Arrays.asList(testFlightOne,testFlightTwo,testFlightThree)), expectedFilteredFlightsList);
    }

    @Test
    void executeReturnsEmptyListIfInputListIsNullOrEmpty() {
        assertEquals(filter.execute(null), Collections.emptyList());
        assertEquals(filter.execute(Collections.emptyList()), Collections.emptyList());
    }



}