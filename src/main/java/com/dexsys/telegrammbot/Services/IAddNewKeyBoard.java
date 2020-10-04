package com.dexsys.telegrammbot.Services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface IAddNewKeyBoard {
    void addNewKeyboard (SendMessage sendMessage);
}
