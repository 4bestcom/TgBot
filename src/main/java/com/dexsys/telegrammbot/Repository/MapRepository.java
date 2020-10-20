package com.dexsys.telegrammbot.Repository;

import com.dexsys.telegrammbot.Domain.User;
import com.dexsys.telegrammbot.Services.UserStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapRepository implements IRepository {

    private final Map<Long, User> users = new HashMap<>();

    @Override
    public void create(long id, String userName, UserStatus userStatus) {
        if (users.containsKey(id)) {
            return;
        }
        users.put(id, createUser(id, userName, userStatus));
    }

    @Override
    public User read(long id) {
        return users.get(id);
    }

    public User createUser(long id, String userName, UserStatus userStatus) {
        return User.builder().chatId(id).userName(userName).userStatus(userStatus).build();
    }
}
