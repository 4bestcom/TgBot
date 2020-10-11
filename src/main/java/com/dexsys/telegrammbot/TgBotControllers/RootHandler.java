package com.dexsys.telegrammbot.TgBotControllers;

import com.dexsys.telegrammbot.Services.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class RootHandler extends TelegramLongPollingBot {
    private static final String TOKEN = "1399901257:AAEVQYREew0BXSH8T7n40YiKI_uI8VRXudQ";
    private static final String NAME_BOT = "BirthdayDexTgBot";
    private boolean isBirthday = true;
    private boolean isRun = true;
    private UserServices userServices;
    private IKeyBoard keyBoard;
    private static Logger log = LoggerFactory.getLogger(RootHandler.class);


    @Autowired
    public void setUserServices(UserServices userServices) {
        this.userServices = userServices;
    }

    @Autowired
    @Qualifier("tgKeyBoardReply")
    public void setKeyBoard(IKeyBoard keyBoard) {
        this.keyBoard = keyBoard;
    }

    @Override
    public void onUpdateReceived(Update update) {

        SendMessage message = new SendMessage();
        long chatId = update.getMessage().getChatId();
        log.info("run input message from tg chatId: " + chatId);
        String userName = update.getMessage().getChat().getUserName();
        String inputTextMg = update.getMessage().getText();
        String regexBirthday = "[0-3][0-9]\\.[01][0-9]\\.[12][09][0-9][0-9]";
        keyBoard.addNewKeyboard(message);
        userServices.createUserToBase(chatId, userName);

        if (inputTextMg.equals("/help")) {
            sendMgFromHelp(message, chatId);
        } else if (inputTextMg.equals("/start") && isRun) {
            isRun = false;
        } else if (inputTextMg.equals("/addBirthday") && !isRun) {
            sendMgFromCommandAddBirthday(message, chatId);
        } else if (inputTextMg.matches(regexBirthday) && !isBirthday && !isRun) {
            sendMgFromMatchesRegexBirthday(message, chatId, inputTextMg);
        } else if (isBirthday && !isRun) {
            sendMgFromDefaultRunning(message, chatId, update);
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

    public void sendMgFromHelp(SendMessage message, long chatId) {
        message.setChatId(chatId);
        message.setText("/help - помощь\n" +
                "/start - запуск бота\n" +
                "/addBirthday - добавление даты рождения пользователя");
        try {
            execute(message);
            log.info("sending message done");
        } catch (TelegramApiException e) {
            log.error("sending message error");
            e.printStackTrace();
        }
    }

    public void sendMgFromMatchesRegexBirthday(SendMessage message, long chatId, String inputTextMg) {
        log.info("User enter Birthday");
        userServices.readUserFromBase(chatId).setBirthDate(inputTextMg);
        log.info("system find this user in DB, added before him Birthday");
        message.setChatId(chatId);
        message.setText("Спасибо");
        isBirthday = true;
        try {
            execute(message);
            log.info("sending message done");
        } catch (TelegramApiException e) {
            log.error("sending message error");
            e.printStackTrace();
        }
    }

    public void sendMgFromCommandAddBirthday(SendMessage message, long chatId) {
        log.info("user press button /addBirthday");
        message.setChatId(chatId);
        message.setText("Введите дату рождения в формате 19.05.1982");
        isBirthday = false;
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("sending message error");
            e.printStackTrace();
        }
    }

    public void sendMgFromDefaultRunning(SendMessage message, long chatId, Update update) {
        log.info("user writing message");
        message.setChatId(chatId);
        message.setText(update.getMessage().getText());
        try {
            execute(message);
            log.info("sending message done");
        } catch (TelegramApiException e) {
            log.error("sending message error");
            e.printStackTrace();
        }
    }

}
