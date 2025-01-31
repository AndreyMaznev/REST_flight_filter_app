package com.manv.flight_filter_app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebhookController {

    private final TelegramLongPollingBot bot;

    public WebhookController(TelegramLongPollingBot bot) {
        this.bot = bot;
    }

    @PostMapping("/webhook")
    public BotApiMethod<?> onWebhookUpdateReceived(@RequestBody Update update) {
        bot.onUpdateReceived(update);
        return null;
    }
}
