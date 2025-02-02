package com.manv.flight_filter_app.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class SegmentDTO {
    private UUID id;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;

    public static SegmentDTO fromSegment(Segment segment) {
        if (segment == null) return null;
        return new SegmentDTO(
                segment.getId(),
                segment.getDepartureDate(),
                segment.getArrivalDate()
        );
    }

    public SegmentDTO() {
    }

    public SegmentDTO(UUID id, LocalDateTime departureDate, LocalDateTime arrivalDate) {
        this.id = id;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt)
                + ']';
    }
}