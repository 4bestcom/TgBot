package com.dexsys.telegrammbot.Services;

import com.dexsys.telegrammbot.Domain.User;
import com.dexsys.telegrammbot.Repository.IRepository;

public class UserServices {

    private IRepository repository;


    public UserServices(IRepository IRepository) {
        this.repository = IRepository;
    }

    public void createUserToBase(long id, String userName) {
        repository.createUserToBase(id, userName);
    }

    public User readUserFromBase(long chatId) {
       return repository.readUserFromBase(chatId);
    }


}
