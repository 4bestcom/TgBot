package com.dexsys.telegrammbot.services.userservices;

import com.dexsys.telegrammbot.domain.User;
import com.dexsys.telegrammbot.domain.UserStatus;

import java.util.List;

public interface IUserAction {
    void createUserToBase(long id, String userName, UserStatus userStatus);

    User readUserFromBase(long id);

    User readUserUsingPhone(String phone);

    List<User> readAllUserFromBase();

    boolean deleteUser(long id);
}
