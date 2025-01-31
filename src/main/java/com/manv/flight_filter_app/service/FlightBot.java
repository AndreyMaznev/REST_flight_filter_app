package com.manv.flight_filter_app.service;

import com.manv.flight_filter_app.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import java.util.List;

@Component
public class FlightBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(FlightBot.class);
    private final FlightService flightService;

    @Autowired
    public FlightBot(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public String getBotUsername() {
        return "FlightService_bot"; // Замените на имя вашего бота
    }

    @Override
    public String getBotToken() {
        return "8057544697:AAEl_E1Dmb37DYEJcx3EU2y5q4gt44zi-Oo"; // Замените на токен вашего бота
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            logger.info("Получено сообщение: {}", messageText); // Логирование входящих сообщений

            switch (messageText) {
                case "/start":
                    sendStartMessage(chatId);
                    break;
                case "/flights":
                    handleFlightsRequest(chatId);
                    break;
                default:
                    sendMsg(chatId, "Неизвестная команда.");
            }
        } else {
            logger.warn("Обновление не содержит сообщения");
        }
    }

    private void sendStartMessage(long chatId) {
        StringBuilder response = new StringBuilder("Привет! Это бот для получения информации о рейсах.\n");
        response.append("Используйте следующие команды:\n");
        response.append("/start - начать взаимодействие\n");
        response.append("/flights - получить список всех рейсов\n");

        sendMsg(chatId, response.toString());
    }

    private void handleFlightsRequest(long chatId) {
        try {
            List<Flight> flights = flightService.getAllUnfilteredFlights(); // Пример без фильтров
            StringBuilder response = new StringBuilder("Рейсы:\n");
            for (Flight flight : flights) {
                response.append(flight.toString()).append("\n");
            }
            sendMsg(chatId, response.toString());
        } catch (Exception e) {
            logger.error("Ошибка при получении данных о рейсах", e);
            sendMsg(chatId, "Ошибка при получении данных о рейсах.");
        }
    }

    private void sendMsg(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(text);
        try {
            execute(sendMessage);
            logger.info("Сообщение отправлено в чат {}", chatId);
        } catch (TelegramApiException e) {
            logger.error("Ошибка при отправке сообщения", e);
        }
    }
}