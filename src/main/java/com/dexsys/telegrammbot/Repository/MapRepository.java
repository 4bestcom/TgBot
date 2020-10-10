package com.dexsys.telegrammbot.Repository;

import com.dexsys.telegrammbot.Domain.User;

import java.util.HashMap;
import java.util.Map;

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
    public User readUserFromBase(long chatId) {
        return users.get(chatId);
    }
}
