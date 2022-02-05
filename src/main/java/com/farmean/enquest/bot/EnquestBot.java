package com.farmean.enquest.bot;

import com.farmean.enquest.models.User;
import com.farmean.enquest.services.users.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Component
public class EnquestBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnquestBot.class);

    private final UsersService usersService;
    private final MessageHandler messageHandler;

    public EnquestBot(UsersService usersService, MessageHandler messageHandler) {
        this.usersService = usersService;
        this.messageHandler = messageHandler;
    }

    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return handleUpdate(update).orElse(null);
    }

    private Optional<BotApiMethod<?>> handleUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();

        } else if (update.hasMessage()) {
            return Optional.of(messageHandler.processMessage(update.getMessage()));
        }

        return Optional.empty();
    }
}
