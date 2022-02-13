package com.farmean.enquest.commands.testme;

import com.farmean.enquest.commands.CallbackCommandHandler;
import com.farmean.enquest.commands.MessageCommandHandler;
import com.farmean.enquest.models.TestQuestion;
import com.farmean.enquest.services.questions.QuestionPool;
import com.farmean.enquest.services.test.TestQuestionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestMeCommandHandler implements CallbackCommandHandler, MessageCommandHandler {

    private final TestQuestionGenerator testQuestionGenerator;
    private final QuestionPool questionPool;

    @Autowired
    public TestMeCommandHandler(TestQuestionGenerator testQuestionGenerator, QuestionPool questionPool) {
        this.testQuestionGenerator = testQuestionGenerator;
        this.questionPool = questionPool;
    }

    @Override
    @Nullable
    public BotApiMethod<?> handle(Message message) {
        TestQuestion testQuestion = testQuestionGenerator.generate();
        long questionId = questionPool.addQuestion(testQuestion);

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        for (int i = 0; i < testQuestion.getOptions().size(); ++i) {
            String option = testQuestion.getOptions().get(i);

            InlineKeyboardButton keyboardRow = new InlineKeyboardButton();
            keyboardRow.setText(option);
            keyboardRow.setCallbackData("/checkme" + ":" + questionId + ":" + i);
            rows.add(List.of(keyboardRow));
        }

        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rows);

        SendMessage reply = new SendMessage();
        reply.setChatId(message.getChatId().toString());
        reply.setText("Как переводится слово \"" + testQuestion.getText() + "\"?");
        reply.setReplyMarkup(replyKeyboardMarkup);

        return reply;
    }

    @Override
    @Nullable
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        TestQuestion testQuestion = testQuestionGenerator.generate();
        long questionId = questionPool.addQuestion(testQuestion);

        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        for (int i = 0; i < testQuestion.getOptions().size(); ++i) {
            String option = testQuestion.getOptions().get(i);

            InlineKeyboardButton keyboardRow = new InlineKeyboardButton();
            keyboardRow.setText(option);
            keyboardRow.setCallbackData("/checkme" + ":" + questionId + ":" + i);
            rows.add(List.of(keyboardRow));
        }

//        EditMessageText result = new EditMessageText();
//        result.setChatId(callbackQuery.getMessage().getChatId().toString());
//        result.setMessageId(callbackQuery.getMessage().getMessageId());
//        result.setText("Как переводится слово \"" + testQuestion.getText() + "\"?");
//        result.setReplyMarkup(new InlineKeyboardMarkup(rows));
        SendMessage result = new SendMessage();
        result.setChatId(callbackQuery.getMessage().getChatId().toString());
        result.setText("Как переводится слово \"" + testQuestion.getText() + "\"?");
        result.setReplyMarkup(new InlineKeyboardMarkup(rows));

        return result;
    }

    @Override
    public String getCommand() {
        return "/testme";
    }
}
