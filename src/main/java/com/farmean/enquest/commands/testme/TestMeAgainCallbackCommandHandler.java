package com.farmean.enquest.commands.testme;

import com.farmean.enquest.commands.CallbackCommandHandler;
import com.farmean.enquest.models.TestQuestion;
import com.farmean.enquest.services.test.TestQuestionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TestMeAgainCallbackCommandHandler implements CallbackCommandHandler {

    private final TestQuestionGenerator testQuestionGenerator;

    @Autowired
    public TestMeAgainCallbackCommandHandler(TestQuestionGenerator testQuestionGenerator) {
        this.testQuestionGenerator = testQuestionGenerator;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        TestQuestion testQuestion = testQuestionGenerator.generate();

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        for (String answer : testQuestion.getOptions()) {
            InlineKeyboardButton keyboardRow = new InlineKeyboardButton();
            keyboardRow.setText(answer);
            keyboardRow.setCallbackData("/testme" + "_" + (answer.equalsIgnoreCase(testQuestion.correctOption()) ? "right" : "wrong"));
            rows.add(List.of(keyboardRow));
        }

        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(callbackQuery.getMessage().getChatId().toString());
        editMessageText.setMessageId(callbackQuery.getMessage().getMessageId());
        editMessageText.setText("Как переводится слово \"" + testQuestion.getText() + "\"?");
        editMessageText.setReplyMarkup(new InlineKeyboardMarkup(rows));

        return editMessageText;
    }

    @Override
    public String getPrefix() {
        return "/testmeagain";
    }
}
