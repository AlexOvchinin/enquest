package com.farmean.enquest.controllers;

import com.farmean.enquest.bot.EnquestBot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController()
@RequestMapping("webhook")
public class WebhookController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookController.class);

    private final EnquestBot enquestBot;

    @Autowired
    public WebhookController(EnquestBot enquestBot) {
        this.enquestBot = enquestBot;
    }

    @GetMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @PostMapping("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BotApiMethod<?> update(@RequestBody Update update) throws TelegramApiValidationException, JsonProcessingException {
        try {
            LOGGER.warn("Update: {}", new ObjectMapper().writeValueAsString(update));

            BotApiMethod<?> response = enquestBot.onWebhookUpdateReceived(update);
            if (response != null) {
                response.validate();
            }

            return response;
        } catch (TelegramApiValidationException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            throw e;
        } catch (Exception e) {
            LOGGER.warn("Random exception", e);
            throw e;
        }
    }
}
