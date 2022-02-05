package com.farmean.enquest.commands.testme;

import com.farmean.enquest.commands.CallbackCommandHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Collections;
import java.util.List;

@Component
public class TestMeCallbackCommandHandler implements CallbackCommandHandler {
    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        String[] parts = callbackQuery.getData().split("_");
        String answer = parts[1];
        boolean rightAnswer = answer.equalsIgnoreCase("right");

        InlineKeyboardButton oneMoreTimeButton = new InlineKeyboardButton();
        oneMoreTimeButton.setText("Попробовать ещё разок");
        oneMoreTimeButton.setCallbackData("/testmeagain_");

        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(callbackQuery.getMessage().getChatId().toString());
        editMessageText.setMessageId(callbackQuery.getMessage().getMessageId());
        editMessageText.setText(rightAnswer ? "Правильно! Возьми пирожок" : "Неправильно! Попробуй ещё");
        editMessageText.setReplyMarkup(new InlineKeyboardMarkup(List.of(List.of(oneMoreTimeButton))));

        return editMessageText;
    }

    @Override
    public String getPrefix() {
        return "/testme";
    }
}
