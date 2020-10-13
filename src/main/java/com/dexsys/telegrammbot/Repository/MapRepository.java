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
    public void createUserToBase(long id, String userName, UserStatus userStatus) {
        System.out.println(users);
        if (users.containsKey(id)) {
            return;
        }
        users.put(id, User.builder().chatId(id).userName(userName).userStatus(userStatus).build());
        System.out.println(users);
    }

    @Override
    public User readUserFromBase(long id) {
        return users.get(id);
    }
}
