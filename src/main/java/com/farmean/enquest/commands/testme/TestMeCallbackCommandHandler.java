package com.farmean.enquest.commands.testme;

import com.farmean.enquest.commands.CallbackCommandHandler;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class TestMeCallbackCommandHandler implements CallbackCommandHandler {
    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        String[] parts = callbackQuery.getData().split("_");
        String answer = parts[2];
        boolean rightAnswer = answer.equalsIgnoreCase("идти");

        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(callbackQuery.getMessage().getChatId().toString());
        editMessageText.setMessageId(callbackQuery.getMessage().getMessageId());
        editMessageText.setText(rightAnswer ? "Правильно! Возьми пирожок" : "Неправильно! Попробуй ещё");
        editMessageText.setReplyMarkup(new InlineKeyboardMarkup());

        return editMessageText;
    }

    @Override
    public String getPrefix() {
        return "/testme";
    }
}
