package com.dexsys.telegrammbot.Controllers;

import com.dexsys.telegrammbot.Domain.User;

import java.util.Map;

public interface IFindUser {
    void findUserAddBirthday (long id, Map<Long, User> users, String inputText);
}
