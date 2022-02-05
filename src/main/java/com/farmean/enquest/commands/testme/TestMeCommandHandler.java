package com.farmean.enquest.commands.testme;

import com.farmean.enquest.commands.CommandHandler;
import com.farmean.enquest.models.TestQuestion;
import com.farmean.enquest.services.test.TestQuestionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestMeCommandHandler implements CommandHandler<TestMeCommand> {

    private final TestQuestionGenerator testQuestionGenerator;

    @Autowired
    public TestMeCommandHandler(TestQuestionGenerator testQuestionGenerator) {
        this.testQuestionGenerator = testQuestionGenerator;
    }

    @Override
    public BotApiMethod<?> handle(Message message) {
        TestQuestion testQuestion = testQuestionGenerator.generate();

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow questionRow = new KeyboardRow();
        questionRow.add(testQuestion.getText());
        rows.add(questionRow);

        for (String answer : testQuestion.getOptions()) {
            KeyboardRow keyboardRow = new KeyboardRow();
            keyboardRow.add(answer);
            rows.add(keyboardRow);
        }

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rows);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        SendMessage reply = new SendMessage();
        reply.setChatId(message.getChatId().toString());
        reply.setText(testQuestion.getText());
        reply.setReplyMarkup(replyKeyboardMarkup);

        return reply;
    }

    @Override
    public String getCommand() {
        return "/testme";
    }
}
