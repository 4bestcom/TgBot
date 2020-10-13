package com.dexsys.telegrammbot.Services;

import com.dexsys.telegrammbot.Domain.User;
import com.dexsys.telegrammbot.Repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServices {

    private IRepository repository;
    @Autowired
    public void setRepository(IRepository repository) {
        this.repository = repository;
    }


    public void createUserToBase(long id, String userName, UserStatus userStatus) {
        repository.createUserToBase(id, userName, userStatus);
    }

    public User readUserFromBase(long id) {
       return repository.readUserFromBase(id);
    }


}
