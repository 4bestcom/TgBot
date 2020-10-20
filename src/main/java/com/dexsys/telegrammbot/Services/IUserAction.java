package com.dexsys.telegrammbot.Services;

import com.dexsys.telegrammbot.Domain.User;

public interface IUserAction {
    void createUserToBase(long id, String userName, UserStatus userStatus);

    User readUserFromBase(long id);
}
