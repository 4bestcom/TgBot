package com.dexsys.telegrammbot.repository;

import com.dexsys.telegrammbot.domain.User;
import com.dexsys.telegrammbot.domain.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MapRepository implements IRepository {
    private static final Logger log = LoggerFactory.getLogger(MapRepository.class);

    private final Map<Long, User> users = new HashMap<>();

    public User save(User user) {
        if (users.containsKey(user.getChatId())) {
            return user;
        }
        users.put(user.getChatId(), user);
        return user;
    }


    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void deleteById(long id) {
        users.remove(id);
    }

    @Override
    public boolean updatePhone(String phone, long chatId) {
        if (users.containsKey(chatId)) {
            users.get(chatId).setPhone(phone);
            return true;
        }
        throw new RuntimeException("user not found");
    }

    @Override
    public boolean updateBirthDate(String birthDate, long chatId) {
        if (users.containsKey(chatId)) {
            users.get(chatId).setBirthDate(birthDate);
            return true;
        }
        throw new RuntimeException("user not found");
    }

    @Override
    public boolean updateUserStatus(UserStatus userStatus, long chatId) {
        if (users.containsKey(chatId)) {
            users.get(chatId).setUserStatus(userStatus);
            return true;
        }
        throw new RuntimeException("user not found");
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findById(String phone) {
        for (Map.Entry<Long, User> entry : users.entrySet()) {
            String phoneUser = entry.getValue().getPhone();
            if (phoneUser.equals(phone)) {
                log.warn("user is found");
                return Optional.ofNullable(entry.getValue());
            }
        }
        throw new RuntimeException("user not found in MockServer");
    }

    public User createUser(long id, String userName, UserStatus userStatus) {
        return User.builder().chatId(id).userName(userName).userStatus(userStatus).build();
    }
}
