package com.farmean.enquest.bot;

import com.farmean.enquest.commands.CallbackCommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class CallbackQueryHandler {

    Map<String, CallbackCommandHandler> commandHandlers = new HashMap<>();

    public CallbackQueryHandler(Collection<CallbackCommandHandler> commandHandlers) {
        commandHandlers.forEach(commandHandler -> this.commandHandlers.put(commandHandler.getPrefix(), commandHandler));
    }


    public BotApiMethod<?> processCallbackQuery(CallbackQuery callbackQuery) {
        String prefix = callbackQuery.getData().substring(0, callbackQuery.getData().indexOf('_'));
        CallbackCommandHandler commandHandler = commandHandlers.get(prefix);
        if (commandHandler != null) {
            return commandHandler.handle(callbackQuery);
        }

        return null;
    }
}
