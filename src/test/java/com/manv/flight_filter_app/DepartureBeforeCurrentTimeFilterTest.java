package com.manv.flight_filter_app;


import com.manv.flight_filter_app.model.filter.DepartureBeforeCurrentTimeFilter;
import com.manv.flight_filter_app.model.Flight;
import com.manv.flight_filter_app.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DepartureBeforeCurrentTimeFilterTest {
    LocalDateTime currentTime = LocalDateTime.of(2024,12,30,12,0);
    DepartureBeforeCurrentTimeFilter filter = new DepartureBeforeCurrentTimeFilter(currentTime);

    Segment testSegmentOne = new Segment(LocalDateTime.of(2024,12,29,3,30)
            , LocalDateTime.of(2024,12,29,12,30));

    Segment testSegmentTwo = new Segment(LocalDateTime.of(2024,12,29,12,0)
            , LocalDateTime.of(2024,12,29,12,30));

    Segment testSegmentThree = new Segment(LocalDateTime.of(2024,12,30,13,30)
            , LocalDateTime.of(2024,12,31,3,30));

    Segment testSegmentFour = new Segment(LocalDateTime.of(2024,12,30,14,30)
            , LocalDateTime.of(2025,1,1,3,30));


    Flight testFlightOne = new Flight(List.of(testSegmentOne));
    Flight testFlightTwo = new Flight(List.of(testSegmentOne, testSegmentTwo));
    Flight testFlightThree = new Flight(List.of(testSegmentOne, testSegmentTwo,testSegmentThree));
    Flight testFlightFour = new Flight(List.of(testSegmentThree,testSegmentFour));

    List <Flight> expectedFilteredFlightsList  = new ArrayList<>(List.of(testFlightFour));

    @Test
    void executeReturnsRightFlightsList() {
        assertEquals (filter.execute(Arrays.asList(testFlightOne,testFlightTwo,testFlightThree,testFlightFour))
                , expectedFilteredFlightsList);
    }

    @Test
    void executeReturnsEmptyListIfInputListIsNullOrEmpty() {
        assertEquals(filter.execute(null), Collections.emptyList());
        assertEquals(filter.execute(Collections.emptyList()), Collections.emptyList());
    }
}