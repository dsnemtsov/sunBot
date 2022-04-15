package ru.yandex.dimas224.sunbot.botapi;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yandex.dimas224.sunbot.SunBot;
import ru.yandex.dimas224.sunbot.service.MenuService;
import ru.yandex.dimas224.sunbot.service.ReplyMessagesService;

@Component
@Slf4j
public class TelegramFacade {
  private final ReplyMessagesService messagesService;
  private final MenuService menuService;
  private final SunBot sunBot;

  private final Map<String, Function<Message, SendMessage>> menu = new HashMap<>();
  private final Map<String, Function<String, BotApiMethod<?>>> callbacks = new HashMap<>();

  public TelegramFacade(ReplyMessagesService messagesService, MenuService menuService, @Lazy SunBot sunBot) {
    this.messagesService = messagesService;
    this.menuService = menuService;
    this.sunBot = sunBot;

    menu.put("/members", this::getMembers);
    menu.put("/scales", this::getScales);
    menu.put("/card_number", message -> messagesService.getReplyMessage(message.getChatId().toString(), "reply.cardNumber"));

    callbacks.put("liza", chatId -> messagesService.getReplyMessage(chatId, "reply.liza"));
    callbacks.put("lena", chatId -> messagesService.getReplyMessage(chatId, "reply.lena"));
    callbacks.put("roma", chatId -> messagesService.getReplyMessage(chatId, "reply.roma"));
    callbacks.put("olga", chatId -> messagesService.getReplyMessage(chatId, "reply.olga"));
    callbacks.put("dima", chatId -> messagesService.getReplyMessage(chatId, "reply.dima"));
    //callbacks.put("doMajor", chatId -> );
  }

  public BotApiMethod<?> handleUpdate(Update update) {
    SendMessage replyMessage = null;

    if (update.hasCallbackQuery()) {
      CallbackQuery callbackQuery = update.getCallbackQuery();
      log.info("New callbackQuery from User: {}, userId: {}, with data: {}", update.getCallbackQuery().getFrom().getUserName(),
              callbackQuery.getFrom().getId(), update.getCallbackQuery().getData());
      return processCallbackQuery(callbackQuery);
    }

    Message message = update.getMessage();
    if (message != null && message.hasText()) {
      log.info("New message from User:{}, userId: {}, chatId: {},  with text: {}",
              message.getFrom().getUserName(), message.getFrom().getId(), message.getChatId(), message.getText());
      replyMessage = handleInputMessage(message);
    }

    return replyMessage;
  }

  private SendMessage handleInputMessage(Message message) {
    String inputMsg = message.getText();

    return menu.getOrDefault(inputMsg, a -> (new SendMessage())).apply(message);
  }

  private BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
    final String chatId = buttonQuery.getMessage().getChatId().toString();
    if (buttonQuery.getData().equals("doMajor")) {
      sunBot.sendPhoto(chatId, "Гамма до-мажор","static/image/domaj.jpg");
      sunBot.sendAudio(chatId, "Гамма до-мажор", "static/audio/domaj.mp3");
    }
    return callbacks.getOrDefault(buttonQuery.getData(), a -> (new SendMessage())).apply(chatId);
  }

  private SendMessage getMembers(Message message) {
    SendMessage replyMessage = messagesService.getReplyMessage(message.getChatId().toString(), "reply.members");
    replyMessage.setReplyMarkup(menuService.getMembersMenu());
    return replyMessage;
  }

  private SendMessage getScales(Message message) {
    SendMessage replyMessage = messagesService.getReplyMessage(message.getChatId().toString(), "reply.scales");
    replyMessage.setReplyMarkup(menuService.getScales());
    return replyMessage;
  }
}

