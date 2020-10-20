package com.dexsys.telegrammbot.TgBotServices;

import com.dexsys.telegrammbot.Services.IUserAction;
import com.dexsys.telegrammbot.Services.UserStatus;
import com.dexsys.telegrammbot.TgBotServices.servicetgkeyboard.IKeyBoard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class RootHandler extends TelegramLongPollingBot {
    private static final String TOKEN = "1399901257:AAEVQYREew0BXSH8T7n40YiKI_uI8VRXudQ";
    private static final String NAME_BOT = "BirthdayDexTgBot";
    private IUserAction iUserAction;
    private IKeyBoard keyBoard;
    private static final Logger log = LoggerFactory.getLogger(RootHandler.class);
    private ITelegramApi telegramApi;

    @Autowired
    public void setTelegramApi(ITelegramApi telegramApi) {
        this.telegramApi = telegramApi;
    }

    @Autowired
    public void setUserServices(IUserAction iUserAction) {
        this.iUserAction = iUserAction;
    }

    @Autowired
    @Qualifier("tgKeyBoardReply")
    public void setKeyBoard(IKeyBoard keyBoard) {
        this.keyBoard = keyBoard;
    }

    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        log.info("run input message from tg chatId: " + chatId);
        String userName = update.getMessage().getChat().getUserName();
        String inputTextMg = update.getMessage().getText();
        String regexBirthday = "[0-3][0-9]\\.[01][0-9]\\.[12][09][0-9][0-9]";
        iUserAction.createUserToBase(chatId, userName, UserStatus.USER_START);
        UserStatus userStatus = iUserAction.readUserFromBase(chatId).getUserStatus();
        SendMessage message = new SendMessage();

        if (inputTextMg.equals("/help")) {
            telegramApi.sendMgFromHelp(chatId, message);
            return;
        }
        if (inputTextMg.equals("/infoAboutMe")) {
            telegramApi.sendMgFromInfoMe(chatId, message, iUserAction);
            return;
        }

        switch (userStatus) {
            case USER_DEFAULT:
                telegramApi.sendMgFromDefaultRunning(chatId, inputTextMg, message);
                break;
            case USER_PRESS_BUTTON:
                if (inputTextMg.matches(regexBirthday)) {
                    telegramApi.sendMgFromMatchesRegexBirthday(chatId, inputTextMg, iUserAction);
                    iUserAction.readUserFromBase(chatId).setUserStatus(UserStatus.USER_DEFAULT);
                } else {
                    telegramApi.sendMgErrorEnterBirthday(chatId);
                }
                break;
            case USER_START:
                if (inputTextMg.equals("/addBirthday")) {
                    iUserAction.readUserFromBase(chatId).setUserStatus(UserStatus.USER_PRESS_BUTTON);
                    telegramApi.sendMgFromCommandAddBirthday(chatId, iUserAction);
                } else {
                    SendMessage message1 = new SendMessage();
                    message1.setReplyMarkup(keyBoard.createNewKeyboard());
                    telegramApi.sendMgFromDefaultRunning(chatId, inputTextMg, message1);
                }
                break;
        }
    }

    @Override
    public String getBotUsername() {
        return NAME_BOT;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
