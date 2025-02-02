package com.manv.flight_filter_app.telegram;

import com.manv.flight_filter_app.telegram.UserContext;
import com.manv.flight_filter_app.model.FlightDTO;
import com.manv.flight_filter_app.service.FlightService;
import com.manv.flight_filter_app.telegram.service.FlightBotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class FlightBot extends TelegramLongPollingBot {
    private final FlightService flightService;
    private final FlightBotService flightBotService;
    private final Environment environment;

    @Autowired
    public FlightBot(FlightService flightService, FlightBotService flightBotService, Environment environment) {
        this.flightService = flightService;
        this.flightBotService = flightBotService;
        this.environment = environment;
    }

    private final Map<Long, UserContext> userContexts = new HashMap<>(); // Хранилище состояний пользователей

    @Override

    public String getBotUsername() {
        return environment.getProperty("BOT_NAME");
    }

    @Override
    public String getBotToken() {
        return environment.getProperty("BOT_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            // Получаем или создаем контекст пользователя
            UserContext userContext = userContexts.computeIfAbsent(chatId, UserContext::new);

            // Обработка команд в зависимости от состояния
            switch (userContext.getState()) {
                case START:
                    handleStartState(chatId, messageText, userContext);
                    break;
                case FILTER_SELECTION:
                    handleFilterSelectionState(chatId, messageText, userContext);
                    break;
                case FILTER_APPLIED:
                    handleFilterAppliedState(chatId, messageText, userContext);
                    break;
                default:
                    sendMsg(chatId, "Неизвестное состояние. Начните заново с /start.");
            }
        }
    }

    // Методы для обработки состояний

    private void handleStartState(long chatId, String messageText, UserContext userContext) {
        if ("/start".equals(messageText)) {
            sendMsg(chatId, "Привет! Выберите действие:\n/flights - Просмотр рейсов");
            userContext.setState(UserState.START);
        } else if ("/flights".equals(messageText)) {
            userContext.setFlights(flightBotService.getAllUnfilteredFlightDTOS());
            sendMsg(chatId, "Выберите фильтр:\n/moreThanWeekDeparture - Отсеять рейсы с departureDate больше недели\n/moreThanOneSegment - Отсеять рейсы с более чем одним сегментом");
            userContext.setState(UserState.FILTER_SELECTION);
        } else {
            sendMsg(chatId, "Неверная команда. Используйте /start для начала.");
        }
    }

    private void handleFilterSelectionState(long chatId, String messageText, UserContext userContext) {
        List<FlightDTO> filteredFlights;

        switch (messageText) {
            case "/moreThanWeekDeparture":
                filteredFlights = applyMoreThanWeekDepartureFilter(userContext.getFlights());
                userContext.setFlights(filteredFlights);
                sendMsg(chatId, "Применен фильтр: Отсеять рейсы с departureDate больше недели.");
                break;
            case "/moreThanOneSegment":
                filteredFlights = applyMoreThanOneSegmentFilter(userContext.getFlights());
                userContext.setFlights(filteredFlights);
                sendMsg(chatId, "Применен фильтр: Отсеять рейсы с более чем одним сегментом.");
                break;
            default:
                sendMsg(chatId, "Неверный фильтр. Выберите один из доступных фильтров.");
                return;
        }

        if (filteredFlights.isEmpty()) {
            sendMsg(chatId, "Нет рейсов после применения фильтра.");
        } else {
            sendMsg(chatId, "Фильтрация завершена. Вы можете продолжить работу с результатами.");
        }

        userContext.setState(UserState.FILTER_APPLIED);
    }

    private void handleFilterAppliedState(long chatId, String messageText, UserContext userContext) {
        if ("/nextAction".equals(messageText)) {
            sendMsg(chatId, "Выберите следующее действие:\n/getResults - Получить результаты\n/reset - Сбросить фильтры");
        } else if ("/getResults".equals(messageText)) {
            sendFilteredFlights(chatId, userContext.getFlights());
        } else if ("/reset".equals(messageText)) {
            userContext.reset();
            sendMsg(chatId, "Фильтры сброшены. Начните заново с /flights.");
            userContext.setState(UserState.START);
        } else {
            sendMsg(chatId, "Неверная команда. Используйте /nextAction для продолжения.");
        }
    }

    // Методы для применения фильтров

    private List<FlightDTO> applyMoreThanWeekDepartureFilter(List<FlightDTO> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now().plusWeeks(1))))
                .toList();
    }

    private List<FlightDTO> applyMoreThanOneSegmentFilter(List<FlightDTO> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().size() <= 1)
                .toList();
    }

    // Вспомогательные методы

    private void sendFilteredFlights(long chatId, List<FlightDTO> flights) {
        StringBuilder response = new StringBuilder("Отфильтрованные рейсы:\n");
        for (FlightDTO flight : flights) {
            response.append(flight.toString()).append("\n");
        }
        sendMsg(chatId, response.toString());
    }

    private void sendMsg(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}