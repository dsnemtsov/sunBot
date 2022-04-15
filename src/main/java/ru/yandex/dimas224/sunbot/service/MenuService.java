package ru.yandex.dimas224.sunbot.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Service
public class MenuService {

  public InlineKeyboardMarkup getMembersMenu() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    InlineKeyboardButton liza = new InlineKeyboardButton("Лиза");
    InlineKeyboardButton lena = new InlineKeyboardButton("Лена");
    InlineKeyboardButton roma = new InlineKeyboardButton("Рома");
    InlineKeyboardButton olga = new InlineKeyboardButton("Оля");
    InlineKeyboardButton dima = new InlineKeyboardButton("Дима");


    //Every button must have callBackData, or else not work !
    liza.setCallbackData("liza");
    lena.setCallbackData("lena");
    roma.setCallbackData("roma");
    olga.setCallbackData("olga");
    dima.setCallbackData("dima");

    List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
    keyboardButtonsRow1.add(liza);
    keyboardButtonsRow1.add(lena);
    keyboardButtonsRow1.add(olga);
    keyboardButtonsRow1.add(roma);
    keyboardButtonsRow1.add(dima);

    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    rowList.add(keyboardButtonsRow1);

    inlineKeyboardMarkup.setKeyboard(rowList);

    return inlineKeyboardMarkup;
  }

  public InlineKeyboardMarkup getScales() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    InlineKeyboardButton doMajor = new InlineKeyboardButton("До-мажор");
    InlineKeyboardButton liaMinor = new InlineKeyboardButton("Ля-минор");

    doMajor.setCallbackData("doMajor");
    liaMinor.setCallbackData("liaMinor");

    List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
    keyboardButtonsRow1.add(doMajor);
    keyboardButtonsRow1.add(liaMinor);

    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    rowList.add(keyboardButtonsRow1);

    inlineKeyboardMarkup.setKeyboard(rowList);

    return inlineKeyboardMarkup;
  }

  public InlineKeyboardMarkup getTheoryMaterials() {
    InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

    InlineKeyboardButton intervals = new InlineKeyboardButton("Интервалы");

    intervals.setCallbackData("intervals");

    List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
    keyboardButtonsRow1.add(intervals);

    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
    rowList.add(keyboardButtonsRow1);

    inlineKeyboardMarkup.setKeyboard(rowList);

    return inlineKeyboardMarkup;
  }
}
