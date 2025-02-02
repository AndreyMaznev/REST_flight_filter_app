package com.manv.flight_filter_app.service;

import com.manv.flight_filter_app.model.Flight;
import com.manv.flight_filter_app.model.FlightDTO;
import com.manv.flight_filter_app.model.Segment;
import com.manv.flight_filter_app.model.SegmentDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SegmentMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public SegmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SegmentDTO toDto (Segment segment) {
        return Objects.isNull(segment) ? null : modelMapper.map(segment, SegmentDTO.class);
    }

    public Segment toEntity (SegmentDTO segmentDTO) {
        return Objects.isNull(segmentDTO) ? null : modelMapper.map(segmentDTO, Segment.class);
    }
}