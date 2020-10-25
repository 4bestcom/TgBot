package com.dexsys.telegrammbot.Services;

import com.dexsys.telegrammbot.Domain.User;

import java.util.List;

public interface IUserAction {
    void createUserToBase(long id, String userName, UserStatus userStatus);

    User readUserFromBase(long id);

    List<User> readAllUserFromBase();

    boolean deleteUser(long id);
}
