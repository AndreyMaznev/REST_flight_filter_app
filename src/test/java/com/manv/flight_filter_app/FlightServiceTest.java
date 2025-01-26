package com.manv.flight_filter_app;

import com.manv.flight_filter_app.model.*;
import com.manv.flight_filter_app.model.filter.DepartureBeforeArrivalFilter;
import com.manv.flight_filter_app.model.filter.DepartureBeforeCurrentTimeFilter;
import com.manv.flight_filter_app.model.filter.TwoHoursOnTheGroundFilter;
import com.manv.flight_filter_app.service.FilterService;
import com.manv.flight_filter_app.service.FlightService;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightServiceTest {
//    LocalDateTime currentTime = LocalDateTime.of(2024,12,30,12,0);
//    FlightService flightService = new FlightService(new FilterService());
//    DepartureBeforeCurrentTimeFilter beforeCurrentTimeFilterTest = new DepartureBeforeCurrentTimeFilter(currentTime);
//    DepartureBeforeArrivalFilter beforeArrivalFilterTest = new DepartureBeforeArrivalFilter();
//    TwoHoursOnTheGroundFilter twoHoursOnTheGroundFilterTest = new TwoHoursOnTheGroundFilter();
//
//
//    Segment testSegmentOne = new Segment(LocalDateTime.of(2024,12,29,3,30)
//            , LocalDateTime.of(2024,12,29,12,0));
//
//    Segment testSegmentTwo = new Segment(LocalDateTime.of(2024,12,29,13,0)
//            , LocalDateTime.of(2024,12,29,15,30));
//
//    Segment testSegmentThree = new Segment(LocalDateTime.of(2024,12,29,18,0)
//            , LocalDateTime.of(2024,12,30,3,30));
//
//    Segment testSegmentFour = new Segment(LocalDateTime.of(2024,12,30,6,30)
//            , LocalDateTime.of(2025,1,1,3,30));
//
//    Segment testSegmentFive = new Segment(LocalDateTime.of(2024,12,30,13,30)
//            , LocalDateTime.of(2024,12,30,15,30));
//
//    Segment testSegmentSix = new Segment(LocalDateTime.of(2024,12,30,16,0)
//            , LocalDateTime.of(2025,1,1,3,30));
//
//    Flight testFlightOne = new Flight(List.of(testSegmentOne));
//    Flight testFlightTwo = new Flight(List.of(testSegmentOne, testSegmentTwo));
//    Flight testFlightThree = new Flight(List.of(testSegmentOne, testSegmentTwo,testSegmentThree));
//    Flight testFlightFour = new Flight(List.of(testSegmentThree,testSegmentFour));
//    Flight testFlightFive = new Flight(List.of(testSegmentFive,testSegmentSix));
//
//    List <FlightFilter> flightFilterList = new ArrayList<>(List.of(
//            beforeCurrentTimeFilterTest,
//            beforeArrivalFilterTest,
//            twoHoursOnTheGroundFilterTest));
//
//    List <Flight> listToFilter = new ArrayList<>(List.of(testFlightOne, testFlightTwo, testFlightThree,
//            testFlightFour, testFlightFive));
//    List <Flight> expectedFilteredFlightList = new ArrayList<>(List.of(testFlightFive));
//    List <Flight> expectedOriginalFlightList = new ArrayList<>(List.of(testFlightOne, testFlightTwo, testFlightThree,
//            testFlightFour, testFlightFive));
//
//    @Test
//    void executeAllFiltersReturnsCorrectFlightsList () {
//        assertEquals(
//                flightService.executeAllFilters(flightFilterList, listToFilter),
//                expectedFilteredFlightList);
//    }
//
//    @Test
//    void executeAllFiltersOnEmptyOrNullListReturnsEmptyList() {
//        assertEquals(flightService.executeAllFilters(flightFilterList, null), Collections.emptyList());
//        assertEquals(flightService.executeAllFilters(flightFilterList, Collections.emptyList()), Collections.emptyList());
//    }
//
//
//    @Test
//    void executeEmptyFilterListReturnsFullOriginalList() {
//        assertEquals(expectedOriginalFlightList, flightService.executeAllFilters(Collections.emptyList(), listToFilter));
//    }
}