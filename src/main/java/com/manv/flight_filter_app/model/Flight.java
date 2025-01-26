package com.manv.flight_filter_app.model;


import java.util.List;
import java.util.stream.Collectors;


public class Flight {
    private final List<Segment> segments;

    public Flight(final List<Segment> segments) {
        this.segments = segments;
    }

    public List<Segment> getSegments() {
        return this.segments;
    }

    @Override
    public String toString() {
        return this.segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }
}
