package com.farmean.enquest.services.bot;

import com.farmean.enquest.configuration.BotConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class EnquestBot extends TelegramWebhookBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnquestBot.class);

    private final BotConfiguration botConfiguration;

    public EnquestBot(BotConfiguration botConfiguration) {
        this.botConfiguration = botConfiguration;
    }

    @Override
    public String getBotUsername() {
        return botConfiguration.getUsername();
    }

    @Override
    public String getBotToken() {
        return botConfiguration.getToken();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(update.getMessage().getText());

            LOGGER.warn("Chat id: {}, text: {}", message.getChatId(), message.getText());

            return message;
        }

        return null;
    }

    @Override
    public String getBotPath() {
        return "update";
    }
}
