package com.dexsys.telegrammbot.TgBotServices.servicetgkeyboard;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class TgKeyBoardReply implements IKeyBoard{

    public ReplyKeyboard createNewKeyboard() {
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(false);
        keyboardRow1.add(new KeyboardButton("/addBirthday"));
        keyboardRow1.add(new KeyboardButton("/infoAboutMe"));
        keyboardRows.add(keyboardRow1);
        keyboard.setKeyboard(keyboardRows);
        return keyboard;
    }
}