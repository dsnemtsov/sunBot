package ru.yandex.dimas224.sunbot.appconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import ru.yandex.dimas224.sunbot.SunBot;
import ru.yandex.dimas224.sunbot.botapi.TelegramFacade;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
  private String webHookPath;
  private String botUserName;
  private String botToken;

  @Bean
  public SunBot getSunBot(TelegramFacade telegramFacade) {
    SunBot sunBot = new SunBot(new DefaultBotOptions(), telegramFacade);
    sunBot.setBotUserName(botUserName);
    sunBot.setBotToken(botUserName);
    sunBot.setWebHookPath(botUserName);

    return sunBot;
  }

  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");

    return messageSource;
  }
}

