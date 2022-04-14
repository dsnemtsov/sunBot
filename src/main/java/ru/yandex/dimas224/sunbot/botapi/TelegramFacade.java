package ru.yandex.dimas224.sunbot.botapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yandex.dimas224.sunbot.service.MenuService;
import ru.yandex.dimas224.sunbot.service.ReplyMessagesService;

@Component
@Slf4j
public class TelegramFacade {
  private final ReplyMessagesService messagesService;
  private final MenuService menuService;

  public TelegramFacade(ReplyMessagesService messagesService, MenuService menuService) {
    this.messagesService = messagesService;
    this.menuService = menuService;
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
    SendMessage replyMessage = null;

    switch (inputMsg) {
      case "/members":
        replyMessage = messagesService.getReplyMessage(message.getChatId().toString(), "reply.members");
        replyMessage.setReplyMarkup(menuService.getMembersMenu());
        break;
      case "/scales":
        replyMessage = messagesService.getReplyMessage(message.getChatId().toString(), "reply.scales");
        replyMessage.setReplyMarkup(menuService.getScales());
        break;
      case "/card_number":
        replyMessage = messagesService.getReplyMessage(message.getChatId().toString(), "reply.cardNumber");
    }
    return replyMessage;
  }

  private BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) {
    final String chatId = buttonQuery.getMessage().getChatId().toString();
    BotApiMethod<?> callBackAnswer = null;
    if (buttonQuery.getData().equals("liza")) {
      callBackAnswer = messagesService.getReplyMessage(chatId, "reply.liza");
    } else if (buttonQuery.getData().equals("lena")) {
      callBackAnswer = messagesService.getReplyMessage(chatId, "reply.lena");
    } else if (buttonQuery.getData().equals("roma")) {
      callBackAnswer = messagesService.getReplyMessage(chatId, "reply.roma");
    } else if (buttonQuery.getData().equals("olga")) {
      callBackAnswer = messagesService.getReplyMessage(chatId, "reply.olga");
    } else if (buttonQuery.getData().equals("dima")) {
      callBackAnswer = messagesService.getReplyMessage(chatId, "reply.dima");
    }
    return callBackAnswer;
  }
}

