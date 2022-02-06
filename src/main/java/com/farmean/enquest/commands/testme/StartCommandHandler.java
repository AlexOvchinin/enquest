package com.farmean.enquest.commands.testme;

import com.farmean.enquest.commands.MessageCommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.annotation.Nullable;

@Component
public class StartCommandHandler implements MessageCommandHandler {
    @Override
    public String getCommand() {
        return "/start";
    }

    @Nullable
    @Override
    public BotApiMethod<?> handle(Message message) {
        return new SendMessage(message.getChatId().toString(), "Привет!");
    }
}
