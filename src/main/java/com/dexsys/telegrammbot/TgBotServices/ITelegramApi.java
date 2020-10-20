package com.dexsys.telegrammbot.TgBotServices;

import com.dexsys.telegrammbot.Services.IUserAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface ITelegramApi {

    void sendMgErrorEnterBirthday(long chatId);

    void sendMgFromHelp(long chatId, SendMessage message);

    void sendMgFromMatchesRegexBirthday(long chatId, String inputTextMg, IUserAction iUserAction);

    void sendMgFromCommandAddBirthday(long chatId, IUserAction iUserAction);

    void sendMgFromDefaultRunning(long chatId, String inputTextMg, SendMessage message);

    void sendMgFromInfoMe(long chatId, SendMessage message, IUserAction iUserAction);
}
