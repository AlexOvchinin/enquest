package com.farmean.enquest.commands;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import javax.annotation.Nullable;

public interface CallbackCommandHandler extends CommandHandler {
    @Nullable
    BotApiMethod<?> handle(CallbackQuery callbackQuery);
}
