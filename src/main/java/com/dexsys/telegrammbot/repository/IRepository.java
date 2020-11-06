package com.dexsys.telegrammbot.repository;

import com.dexsys.telegrammbot.domain.User;
import com.dexsys.telegrammbot.domain.UserStatus;

import java.util.List;
import java.util.Optional;

public interface IRepository {

    User save(User user);

    List<User> findAll();

    Optional<User> findById(long id);

    Optional<User> findByPhone(String phone);

    void deleteById(long id);

    boolean updatePhone(String phone, long chatId);

    boolean updateBirthDate(String birthDate, long chatId);

    boolean updateUserStatus(UserStatus userStatus, long chatId);

//    User createUser(long id, String userName, UserStatus userStatus);




}
