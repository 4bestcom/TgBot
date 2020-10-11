package com.dexsys.telegrammbot.Repository;

import com.dexsys.telegrammbot.Domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapRepository implements IRepository {

    private Map<Long, User> users = new HashMap<>();

    @Override
    public void createUserToBase(long id, String userName) {
        if (users.containsKey(id)) {
            return;
        }
        users.put(id, User.builder().chatId(id).userName(userName).build());
    }

    @Override
    public User readUserFromBase(long id) {
        return users.get(id);
    }
}
