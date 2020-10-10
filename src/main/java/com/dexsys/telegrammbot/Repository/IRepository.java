package com.dexsys.telegrammbot.Repository;

import com.dexsys.telegrammbot.Domain.User;

public interface IRepository {

    void createUserToBase(long id, String userName);

    User readUserFromBase(long chatId);


}
