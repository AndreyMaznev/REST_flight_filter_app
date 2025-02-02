package com.manv.flight_filter_app.config;

import com.manv.flight_filter_app.telegram.FlightBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializer {

    private final FlightBot flightBot;

    @Autowired
    public BotInitializer(FlightBot flightBot) {
        this.flightBot = flightBot;

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(flightBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}