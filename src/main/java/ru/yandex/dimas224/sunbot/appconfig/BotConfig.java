package ru.yandex.dimas224.sunbot.appconfig;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import ru.yandex.dimas224.sunbot.SunBot;
import ru.yandex.dimas224.sunbot.botapi.TelegramFacade;

@Setter
@Getter
@Configuration
@AllArgsConstructor
public class BotConfig {
  private final TelegramConfig telegramConfig;

  @Bean
  public SetWebhook setWebhookInstance() {
    return SetWebhook.builder().url(telegramConfig.getWebhookPath()).build();
  }

  @Bean
  public SunBot getSunBot(TelegramFacade telegramFacade) {
    SunBot sunBot = new SunBot(new DefaultBotOptions(), telegramFacade);
    sunBot.setBotUserName(telegramConfig.getBotName());
    sunBot.setBotToken(telegramConfig.getBotToken());
    sunBot.setWebHookPath(telegramConfig.getWebhookPath());

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

