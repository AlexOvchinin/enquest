package com.farmean.enquest.services.bot;

import com.farmean.enquest.configuration.BotConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
public class TelegramService  {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramService.class);

    private final BotConfiguration botConfiguration;

    public TelegramService(BotConfiguration botConfiguration) {
        this.botConfiguration = botConfiguration;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void registerBot() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new EnquestBot(botConfiguration));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
