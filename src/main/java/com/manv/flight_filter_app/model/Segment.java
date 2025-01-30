package com.manv.flight_filter_app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@AllArgsConstructor
@Entity
@Table (name = "segments")
public class Segment {

    @Id
    @Column (name = "segment_id")
    private UUID id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column (name = "departure_date")
    private LocalDateTime departureDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column (name = "arrival_date")
    private LocalDateTime arrivalDate;

    @Column(name = "flight_id", nullable = false)
    private UUID flightId;


    public Segment(LocalDateTime departureDate, LocalDateTime arrivalDate) {
        this.id = UUID.randomUUID();
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    @ManyToOne
    @JoinColumn (name = "flight_id", referencedColumnName = "flight_id", nullable = false, insertable = false, updatable = false)
    @JsonBackReference
    public Flight mainFlight;

    public Segment() {
        this.id = UUID.randomUUID();
    }

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
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

    public Flight getMainFlight() {
        return mainFlight;
    }

    public void setMainFlight(Flight mainFlight) {
        this.mainFlight = mainFlight;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt)
                + ']';
    }
}