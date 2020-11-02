package com.dexsys.telegrammbot.services.tgbotservices;

import com.dexsys.telegrammbot.services.userservices.IUserAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface ITelegramApi {

    void sendMgErrorEnterBirthday(long chatId);

    void sendMgFromHelp(long chatId, SendMessage message);

    void sendMgFromMatchesRegexBirthday(long chatId, String inputTextMg, IUserAction iUserAction);

    void sendMgFromCommandAddBirthday(long chatId, IUserAction iUserAction);

    void sendMgFromDefaultRunning(long chatId, String inputTextMg, SendMessage message);

    void sendMgFromInfoMe(long chatId, SendMessage message, IUserAction iUserAction);

    void sendMgEnterPhone(SendMessage message, long chatId, String inputTextMg, IUserAction userAction);
}
