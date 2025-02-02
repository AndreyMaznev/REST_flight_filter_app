package com.manv.flight_filter_app.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table (name = "flights")
public class Flight {

    @Id
    @Column (name = "flight_id", columnDefinition = "UUID")
    private UUID flightId;

    @Column (name = "flight_number")
    private String flightNumber;

    @Column (name = "owner_company")
    private String ownerCompany;
    @Column (name = "short_description")
    private String shortDescription;

    @OneToMany (mappedBy = "mainFlight", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Segment> segments;

    public Flight(String flightNumber, String ownerCompany, String shortDescription, List<Segment> segments) {
        this.flightNumber = flightNumber;
        this.ownerCompany = ownerCompany;
        this.shortDescription = shortDescription;
        this.segments = segments;
        this.flightId = UUID.randomUUID();
    }

    public Flight(List<Segment> segments) {
        this.segments = segments;
    }

    public Flight() {
        this.flightId = UUID.randomUUID();
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

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    @Override
    public String toString() {
        return this.segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
