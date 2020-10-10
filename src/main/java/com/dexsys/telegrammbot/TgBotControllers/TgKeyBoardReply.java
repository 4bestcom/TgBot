package com.dexsys.telegrammbot.TgBotControllers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class TgKeyBoardReply {
    private int count = 0;

    public void addNewKeyboard(SendMessage message) {
        if (count > 1) return;
        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboard.setSelective(true);
        keyboard.setResizeKeyboard(true);
        keyboard.setOneTimeKeyboard(true);
        keyboardRow1.add(new KeyboardButton("/addBirthday"));
        keyboardRows.add(keyboardRow1);
        keyboard.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboard);
        count++;
    }
}