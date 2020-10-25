package com.dexsys.telegrammbot.Repository;

import com.dexsys.telegrammbot.Domain.User;
import com.dexsys.telegrammbot.Services.UserStatus;

import java.util.List;

public interface IRepository {

    void create(long id, String userName, UserStatus userStatus);

    User read(long id);

    User read (String phone);

    List<User> readAll();

    boolean delete(long id);



}
