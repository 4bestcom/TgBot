package com.dexsys.telegrammbot.repository;

import com.dexsys.telegrammbot.domain.User;
import com.dexsys.telegrammbot.domain.UserStatus;

import java.util.List;

public interface IRepository {

    void create(long id, String userName, UserStatus userStatus);

    User read(long id);

    User read (String phone);

    List<User> readAll();

    boolean delete(long id);

    boolean updatePhone(String phone, long chatId);

    boolean updateBirthDate(String birthDate, long chatId);

    boolean updateUserStatus(UserStatus userStatus, long chatId);



}
