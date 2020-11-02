package com.dexsys.telegrammbot.services.userservices;

import com.dexsys.telegrammbot.domain.User;
import com.dexsys.telegrammbot.repository.IRepository;
import com.dexsys.telegrammbot.domain.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServices implements IUserAction {

    private IRepository repository;

    @Autowired
    public void setRepository(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUserToBase(long id, String userName, UserStatus userStatus) {
        repository.create(id, userName, userStatus);
    }
    @Override
    public User readUserFromBase(long id) {
        return repository.read(id);
    }

    @Override
    public User readUserUsingPhone(String phone) {
        return repository.read(phone);
    }

    @Override
    public boolean deleteUser(long id) {
        return repository.delete(id);
    }

    @Override
    public List<User> readAllUserFromBase() {
        return repository.readAll();
    }
}
