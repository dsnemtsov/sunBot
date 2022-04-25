package ru.yandex.dimas224.sunbot;

import java.io.File;
import java.nio.file.Path;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
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

  @SneakyThrows
  public void sendAudio(String chatId, String audioPath) {
    Path filePath = Path.of("src/main/resources/static/" + audioPath);
    File audio = filePath.toFile();
    InputFile inputAudio = new InputFile(audio);
    SendAudio sendAudio = new SendAudio();
    sendAudio.setAudio(inputAudio);
    sendAudio.setChatId(chatId);

    execute(sendAudio);
  }

  @SneakyThrows
  public void sendPhoto(String chatId, String imagePath) {
    Path filePath = Path.of("src/main/resources/static/" + imagePath);
    File image = filePath.toFile();
    InputFile inputImage = new InputFile(image);
    SendPhoto sendPhoto = new SendPhoto();
    sendPhoto.setPhoto(inputImage);
    sendPhoto.setChatId(chatId);

    execute(sendPhoto);
  }

  @SneakyThrows
  public void sendVideo(String chatId, String videoPath) {
    Path filePath = Path.of("src/main/resources/static/" + videoPath);
    File video = filePath.toFile();
    InputFile inputVideo = new InputFile(video);
    SendVideo sendVideo = new SendVideo();
    sendVideo.setVideo(inputVideo);
    sendVideo.setChatId(chatId);

    execute(sendVideo);
  }
}
