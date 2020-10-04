package com.dexsys.telegrammbot.Services;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class TgKeyBoard{

    public void enterNewKeyBoard (IAddNewKeyBoard iAddNewKeyBoard, SendMessage sendMessage){
        iAddNewKeyBoard.addNewKeyboard(sendMessage);

    }
}
