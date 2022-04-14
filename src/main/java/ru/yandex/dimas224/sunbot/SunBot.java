package ru.yandex.dimas224.sunbot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SunBot extends TelegramWebhookBot {
  private String webHookPath;
  private String botUserName;
  private String botToken;

  public SunBot(DefaultBotOptions botOptions) {
    super(botOptions);
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

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {

      SendMessage sendMessage = new SendMessage();
      sendMessage.setChatId(update.getMessage().getChatId().toString());
      sendMessage.setText("Ответ");

      return sendMessage;
    }
    return null;
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
}
