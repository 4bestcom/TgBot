package com.dexsys.telegrammbot.RootHandler;

import com.dexsys.telegrammbot.Data.User;

import java.util.Map;

public interface IFindUser {
    void findUserAddBirthday (long id, Map<Long, User> users, String inputText);
}
