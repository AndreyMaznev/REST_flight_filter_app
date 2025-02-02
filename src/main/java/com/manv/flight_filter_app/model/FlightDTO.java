package com.manv.flight_filter_app.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class FlightDTO {
    private UUID flightId;
    private String flightNumber;
    private String ownerCompany;
    private String shortDescription;
    private List<SegmentDTO> segments;

    public static FlightDTO fromFlight(Flight flight) {
        if (flight == null) return null;
        return new FlightDTO(
                flight.getFlightId(),
                flight.getFlightNumber(),
                flight.getOwnerCompany(),
                flight.getShortDescription(),
                flight.getSegments() != null ? flight.getSegments()
                        .stream()
                        .map(SegmentDTO::fromSegment).toList()
                        : Collections.emptyList());
    }

    public FlightDTO() {
    }

    public FlightDTO(UUID flightId, String flightNumber, String ownerCompany, String shortDescription, List<SegmentDTO> segments) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.ownerCompany = ownerCompany;
        this.shortDescription = shortDescription;
        this.segments = segments;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "Рейс №%s (%s)\n" +
                        "Компания: %s\n" +
                        "Описание: %s\n" +
                        "Сегменты:\n",
                flightNumber,
                flightId,
                ownerCompany,
                shortDescription
        ));
        if (segments != null && !segments.isEmpty()) {
            for (SegmentDTO segment : segments) {
                sb.append(segment.toString()).append("\n");
            }
        } else {
            sb.append("Нет сегментов для этого рейса.\n");
        }
        return sb.toString();
    }

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public List<SegmentDTO> getSegments() {
        return segments;
    }

    public void setSegments(List<SegmentDTO> segments) {
        this.segments = segments;
    }
}