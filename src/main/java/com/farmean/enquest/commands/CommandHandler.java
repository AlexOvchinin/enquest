package com.farmean.enquest.commands;

import com.farmean.enquest.commands.Command;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface CommandHandler<T extends Command> {
    BotApiMethod<?> handle(Message message);

    String getCommand();
}
