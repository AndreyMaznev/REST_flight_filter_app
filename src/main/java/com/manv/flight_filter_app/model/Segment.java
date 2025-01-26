package com.manv.flight_filter_app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;



public class Segment {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime departureDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private final LocalDateTime arrivalDate;

    public Segment(LocalDateTime dep, LocalDateTime arr) {
        departureDate = dep;
        arrivalDate = arr;
//        arrivalDate = Objects.requireNonNull(arr);
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }
    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt)
                + ']';
    }
}