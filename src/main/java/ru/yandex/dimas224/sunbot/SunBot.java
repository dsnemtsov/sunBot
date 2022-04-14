package ru.yandex.dimas224.sunbot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yandex.dimas224.sunbot.botapi.TelegramFacade;

public class SunBot extends TelegramWebhookBot {
  private String webHookPath;
  private String botUserName;
  private String botToken;
  private final TelegramFacade telegramFacade;

  public SunBot(DefaultBotOptions botOptions, TelegramFacade telegramFacade) {
    super(botOptions);
    this.telegramFacade = telegramFacade;
  }

  @Override
  public String getBotToken() {
    return botToken;
  }

  @Override
  public String getBotUsername() {
    return botUserName;
  }

  @Override
  public String getBotPath() {
    return webHookPath;
  }

  public void setWebHookPath(String webHookPath) {
    this.webHookPath = webHookPath;
  }

  public void setBotUserName(String botUserName) {
    this.botUserName = botUserName;
  }

  public void setBotToken(String botToken) {
    this.botToken = botToken;
  }

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    return telegramFacade.handleUpdate(update);
  }
}
