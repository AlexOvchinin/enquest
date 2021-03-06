package com.farmean.enquest.commands.testme;

import com.farmean.enquest.commands.CallbackCommandHandler;
import com.farmean.enquest.models.TestQuestion;
import com.farmean.enquest.services.test.persistence.TestQuestionPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Component
public class CheckMeCommandHandler implements CallbackCommandHandler {

    private final TestQuestionPool testQuestionPool;

    @Autowired
    public CheckMeCommandHandler(TestQuestionPool testQuestionPool) {
        this.testQuestionPool = testQuestionPool;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery callbackQuery) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(callbackQuery.getMessage().getChatId().toString());
        editMessageText.setMessageId(callbackQuery.getMessage().getMessageId());

        String[] parts = callbackQuery.getData().split(":");
        long questionId = Long.parseLong(parts[1]);
        int selectedOption = Integer.parseInt(parts[2]);
        TestQuestion testQuestion = testQuestionPool.getQuestion(questionId);
        if (testQuestion != null) {
            int rightOption = -1;
            for (int i = 0; i < testQuestion.getOptions().size(); ++i) {
                String option = testQuestion.getOptions().get(i);
                if (option.equalsIgnoreCase(testQuestion.correctOption())) {
                    rightOption = i;
                    break;
                }
            }

            editMessageText.setText(selectedOption == rightOption ?
                    "??????????????????! ???????????? ??????????????" :
                    String.format("\"??????????????????????! ???????????????????? ?????????? ???????????????? \"%s\": %s",
                            testQuestion.getText(),
                            testQuestion.correctOption())
            );
        } else {
            editMessageText.setText("???????????????? ?????????????????? ?? ?????????????????????? ????????????????????");
        }

        InlineKeyboardButton oneMoreTimeButton = new InlineKeyboardButton();
        oneMoreTimeButton.setText("?????????????????? ????????????");
        oneMoreTimeButton.setCallbackData("/testme");

        editMessageText.setReplyMarkup(new InlineKeyboardMarkup(List.of(List.of(oneMoreTimeButton))));

        return editMessageText;
    }

    @Override
    public String getCommand() {
        return "/checkme";
    }
}
