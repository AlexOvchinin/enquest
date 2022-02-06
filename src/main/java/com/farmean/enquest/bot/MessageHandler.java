package com.farmean.enquest.bot;

import com.farmean.enquest.commands.MessageCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class MessageHandler {

    Map<String, MessageCommandHandler> commandHandlers = new HashMap<>();

    @Autowired
    public MessageHandler(Collection<MessageCommandHandler> messageCommandHandlers) {
        messageCommandHandlers.forEach(messageCommandHandler -> this.commandHandlers.put(messageCommandHandler.getCommand(), messageCommandHandler));
    }

    public BotApiMethod<?> processMessage(Message message) {
        MessageCommandHandler messageCommandHandler = message.hasText() ? commandHandlers.get(message.getText().toLowerCase()) : null;
        if (messageCommandHandler != null) {
            return messageCommandHandler.handle(message);
        } else {
            SendMessage sendMessage = new SendMessage(); // Create a SendMessage object with mandatory fields
            sendMessage.setChatId(message.getChatId().toString());
            sendMessage.setText("Неизвестная команда, попробуйте написать / чтобы узнать список доступных команд");
            return sendMessage;
        }
    }
}
