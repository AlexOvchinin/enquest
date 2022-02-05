package com.farmean.enquest.controllers;

import com.farmean.enquest.configuration.BotConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.generics.TelegramBot;

@RestController()
@RequestMapping("bot")
public class WebhookController {

    private final BotConfiguration botConfiguration;

    @Autowired
    public WebhookController(BotConfiguration botConfiguration) {
        this.botConfiguration = botConfiguration;
    }

    @GetMapping("/")
    public String helloWorld() {
        return "helloWorld";
    }
}
