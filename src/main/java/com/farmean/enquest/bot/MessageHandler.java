package com.farmean.enquest.bot;

import com.farmean.enquest.commands.CommandHandler;
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

    Map<String, CommandHandler<?>> commandHandlers = new HashMap<>();

    @Autowired
    public MessageHandler(Collection<CommandHandler<?>> commandHandlers) {
        commandHandlers.forEach(commandHandler -> this.commandHandlers.put(commandHandler.getCommand(), commandHandler));
    }

    public BotApiMethod<?> processMessage(Message message) {
        CommandHandler<?> commandHandler = message.hasText() ? commandHandlers.get(message.getText().toLowerCase()) : null;
        if (commandHandler != null) {
            return commandHandler.handle(message);
        } else {
            SendMessage sendMessage = new SendMessage(); // Create a SendMessage object with mandatory fields
            sendMessage.setChatId(message.getChatId().toString());
            sendMessage.setText("Неизвестная команда, попробуйте написать / чтобы узнать список доступных команд");
            return sendMessage;
        }
    }
}
