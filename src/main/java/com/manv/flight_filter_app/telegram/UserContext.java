package com.manv.flight_filter_app.telegram;

import com.manv.flight_filter_app.model.FlightDTO;

import java.util.List;


public class UserContext {

    private UserState state = UserState.START;
    private List<FlightDTO> flights;
    private long chatId;

    public UserContext(long chatId) {
        this.chatId = chatId;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public List<FlightDTO> getFlights() {
        return flights;
    }

    public void setFlights(List<FlightDTO> flights) {
        this.flights = flights;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public void reset() {
        this.state = UserState.START;
        this.flights = null;
    }
}