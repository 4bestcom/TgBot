package com.dexsys.telegrammbot.Repository;

import com.dexsys.telegrammbot.Domain.User;
import com.dexsys.telegrammbot.Services.UserStatus;

public interface IRepository {

    void create(long id, String userName, UserStatus userStatus);

    User read(long id);


}
