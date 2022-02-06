package com.farmean.enquest.commands;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.annotation.Nullable;

public interface MessageCommandHandler extends CommandHandler {
    @Nullable
    BotApiMethod<?> handle(Message message);
}
