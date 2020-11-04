package com.dexsys.telegrammbot.services.userservices;

import com.dexsys.telegrammbot.domain.User;
import com.dexsys.telegrammbot.repository.IRepository;
import com.dexsys.telegrammbot.domain.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServices implements IUserAction {

    private IRepository repository;

    @Autowired
    @Qualifier("connectionFactory")
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


    @Override
    public boolean updatePhone(String phone, long chatId) {
        return repository.updatePhone(phone, chatId);
    }

    @Override
    public boolean updateUserStatus(UserStatus userStatus, long chatId) {
        return repository.updateUserStatus(userStatus, chatId);
    }

    @Override
    public boolean updateBirthDate(String birthDate, long chatId) {
        return repository.updateBirthDate(birthDate, chatId);
    }
}
