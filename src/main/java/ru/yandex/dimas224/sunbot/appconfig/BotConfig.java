package ru.yandex.dimas224.sunbot.appconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import ru.yandex.dimas224.sunbot.SunBot;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
  private String webHookPath;
  private String botUserName;
  private String botToken;

  @Bean
  public SunBot getSunBot() {
    SunBot sunBot = new SunBot(new DefaultBotOptions());
    sunBot.setBotUserName(botUserName);
    sunBot.setBotToken(botUserName);
    sunBot.setWebHookPath(botUserName);

    return sunBot;
  }
}

