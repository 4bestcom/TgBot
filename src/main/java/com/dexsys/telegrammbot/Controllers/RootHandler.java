package com.dexsys.telegrammbot.Controllers;

import com.dexsys.telegrammbot.Services.IAddNewKeyBoard;
import com.dexsys.telegrammbot.Services.TgKeyBoard;
import com.dexsys.telegrammbot.Domain.User;
import com.dexsys.telegrammbot.Services.TgKeyBoardReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.HashMap;
import java.util.Map;


public class RootHandler extends TelegramLongPollingBot implements IFindUser {
    private static final String TOKEN = "1399901257:AAEVQYREew0BXSH8T7n40YiKI_uI8VRXudQ";
    private static final String NAME_BOT = "BirthdayDexTgBot";
    private Map<Long, User> users = new HashMap<>();
    private boolean isBd = true;
    private static Logger log = LoggerFactory.getLogger(RootHandler.class);


    @Override
    public void onUpdateReceived(Update update) {
        SendMessage message = new SendMessage();
        long chatId = update.getMessage().getChatId();
        log.info("run input message from tg chatId: " + chatId);
        String userName = update.getMessage().getChat().getUserName();
        String inputTextMg = update.getMessage().getText();
        String regexBirthday = "[0-3][0-9]\\.[01][0-9]\\.[12][09][0-9][0-9]";

        if (inputTextMg.matches(regexBirthday) && !isBd) {
            log.info("User enter Birthday");
            findUserAddBirthday(chatId, users, inputTextMg);
            log.info("system find this user in DB, added before him Birthday");
            message.setChatId(update.getMessage().getChatId());
            message.setText("Спасибо");
            isBd = true;
            try {
                execute(message);
                log.info("sending message done");
            } catch (TelegramApiException e) {
                log.error("sending message error");
                e.printStackTrace();
            }
        } else if (inputTextMg.equals("/addBirthday")) {
            log.info("user press button /addBirthday");
            message.setChatId(update.getMessage().getChatId());
            message.setText(update.getMessage().getText());
            message.setText("Введите дату рождения в формате 19.05.1982");
            isBd = false;
            try {
                execute(message);
            } catch (TelegramApiException e) {
                log.error("sending message error");
                e.printStackTrace();
            }
        } else if (isBd) {
            TgKeyBoard tgKeyBoard = new TgKeyBoard();
            IAddNewKeyBoard addNewKeyBoardReply = new TgKeyBoardReply();
            log.info("user writing message");
            message.setChatId(update.getMessage().getChatId());
            message.setText(update.getMessage().getText());
            tgKeyBoard.enterNewKeyBoard(addNewKeyBoardReply, message);
            try {
                execute(message);
                log.info("sending message done");
                if (users.containsKey(chatId)) {
                    log.warn("user with this chatId is in the DB");
                    return;
                }
                users.put(chatId, addUserFromTgChat(chatId, userName));
            } catch (TelegramApiException e) {
                log.error("sending message error");
                e.printStackTrace();
            }
        }
        log.info("all users in DB: " + users.toString());
    }

    @Override
    public String getBotUsername() {
        return NAME_BOT;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }


    public User addUserFromTgChat(long id, String userName) {
        return User.builder().chatId(id).userName(userName).build();
    }

    @Override
    public void findUserAddBirthday(long id, Map<Long, User> users, String inputText) {
        users.get(id).setBirthDate(inputText);
    }

}
