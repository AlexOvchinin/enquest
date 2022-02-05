package com.farmean.enquest.bot;

import com.farmean.enquest.commands.CommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Collection;
import java.util.Map;

@Component
public class CallbackQueryHandler {

    Map<String, CommandHandler<?>> commandHandlerMap;

    private final Collection<CommandHandler<?>> commandHandlers;

    public CallbackQueryHandler(Collection<CommandHandler<?>> commandHandlers) {
        this.commandHandlers = commandHandlers;
    }


    public BotApiMethod<?> processCallbackQuery(CallbackQuery callbackQuery) {
        return null;
    }
}
