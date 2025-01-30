package com.manv.flight_filter_app.repository;

import com.manv.flight_filter_app.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FlightRepository extends JpaRepository<Flight, UUID> {
    List <Flight> findAll();

    @Query(value = "SELECT DISTINCT f FROM Flight f JOIN f.segments s WHERE " +
            "(cast(:departureDateFrom as timestamp) IS NULL OR s.departureDate >= cast(:departureDateFrom as timestamp)) AND " +
            "(cast(:departureDateTo as timestamp) IS NULL OR s.departureDate <= cast(:departureDateTo as timestamp)) AND " +
            "(cast(:arrivalDateFrom as timestamp) IS NULL OR s.arrivalDate >= cast(:arrivalDateFrom as timestamp)) AND " +
            "(cast(:arrivalDateTo as timestamp) IS NULL OR s.arrivalDate <= cast(:arrivalDateTo as timestamp))")
    List<Flight> findBySegmentDatesBetween(
            @Param("departureDateFrom") LocalDateTime departureDateFrom,
            @Param("departureDateTo") LocalDateTime departureDateTo,
            @Param("arrivalDateFrom") LocalDateTime arrivalDateFrom,
            @Param("arrivalDateTo") LocalDateTime arrivalDateTo);

//    @Query(value = "SELECT DISTINCT f FROM Flight f JOIN f.segments s WHERE " +
//            "(cast(:fromDateTime as timestamp) IS NULL OR s.departureDate >= cast(:fromDateTime as timestamp)) AND " +
//            "(cast(:toDateTime as timestamp) IS NULL OR s.departureDate <= cast(:toDateTime as timestamp))",
//            nativeQuery = false)
//    List<Flight> findBySegmentDatesBetween(@Param("fromDateTime") LocalDateTime fromDateTime, @Param("toDateTime") LocalDateTime toDateTime);

}

