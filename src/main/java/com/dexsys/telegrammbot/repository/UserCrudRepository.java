package com.dexsys.telegrammbot.repository;

import com.dexsys.telegrammbot.domain.User;
import com.dexsys.telegrammbot.domain.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class UserCrudRepository implements IRepository {
    private IUserCrudRepository crudRepository;

    @Autowired
    public void setCrudRepository(IUserCrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public User save(User user) {
        Optional<User> userFind = crudRepository.findById(user.getChatId());
        if (userFind.isPresent()){
            return userFind.get();
        }
        return crudRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) crudRepository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return crudRepository.findById(id);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return crudRepository.findByPhone(phone);
    }

    @Override
    public void deleteById(long id) {
        crudRepository.deleteById(id);
    }

    @Override
    public boolean updatePhone(String phone, long chatId) {
        Optional<User> user = crudRepository.findById(chatId);
        if (user.isPresent()) {
            User userNew = user.get();
            userNew.setPhone(phone);
            crudRepository.save(userNew);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBirthDate(String birthDate, long chatId) {
        Optional<User> user = crudRepository.findById(chatId);
        if (user.isPresent()) {
            User userNew = user.get();
            userNew.setBirthDate(birthDate);
            crudRepository.save(userNew);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUserStatus(UserStatus userStatus, long chatId) {
        Optional<User> user = crudRepository.findById(chatId);
        if (user.isPresent()) {
            User userNew = user.get();
            userNew.setUserStatus(userStatus);
            crudRepository.save(userNew);
            return true;
        }
        return false;
    }
}
