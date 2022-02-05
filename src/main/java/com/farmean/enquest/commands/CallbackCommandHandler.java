package com.farmean.enquest.commands;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CallbackCommandHandler {
    BotApiMethod<?> handle(CallbackQuery callbackQuery);

    String getPrefix();
}
