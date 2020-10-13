package com.dexsys.telegrammbot.TgBotServices;

import com.dexsys.telegrammbot.Services.UserServices;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface ITelegramApi {

    void sendMgErrorEnterBirthday(long chatId);

    void sendMgFromHelp(long chatId, SendMessage message);

    void sendMgFromMatchesRegexBirthday(long chatId, String inputTextMg, UserServices userServices);

    void sendMgFromCommandAddBirthday(long chatId, UserServices userServices);

    void sendMgFromDefaultRunning(long chatId, String inputTextMg, SendMessage message);
}
