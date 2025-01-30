package com.manv.flight_filter_app.repository;

import com.manv.flight_filter_app.model.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SegmentRepository extends JpaRepository <Segment, UUID> {
    List<Segment> findByMainFlightFlightId(UUID flightId);
}
