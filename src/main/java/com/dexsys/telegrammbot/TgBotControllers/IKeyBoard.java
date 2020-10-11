package com.dexsys.telegrammbot.TgBotControllers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface IKeyBoard {

    void addNewKeyboard(SendMessage message);
}
