package com.farmean.enquest.bot;

import com.farmean.enquest.commands.CallbackCommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class CallbackQueryHandler {

    Map<String, CallbackCommandHandler> commandHandlers = new HashMap<>();

    public CallbackQueryHandler(Collection<CallbackCommandHandler> commandHandlers) {
        commandHandlers.forEach(commandHandler -> this.commandHandlers.put(commandHandler.getCommand(), commandHandler));
    }

    @Nullable
    public BotApiMethod<?> processCallbackQuery(CallbackQuery callbackQuery) {
        int colonIndex = callbackQuery.getData().indexOf(':');
        String prefix = colonIndex == -1 ? callbackQuery.getData() : callbackQuery.getData().substring(0, colonIndex);
        CallbackCommandHandler commandHandler = commandHandlers.get(prefix);
        if (commandHandler != null) {
            return commandHandler.handle(callbackQuery);
        }

        return null;
    }
}
