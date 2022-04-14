package ru.yandex.dimas224.sunbot.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yandex.dimas224.sunbot.SunBot;

@RestController
public class WebHookController {
  private final SunBot sunBot;

  public WebHookController(SunBot sunBot) {
    this.sunBot = sunBot;
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
    return sunBot.onWebhookUpdateReceived(update);
  }
}