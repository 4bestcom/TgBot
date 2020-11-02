package com.dexsys.telegrammbot.repository;

import com.dexsys.telegrammbot.domain.User;
import com.dexsys.telegrammbot.domain.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MapRepository implements IRepository {
    private static final Logger log = LoggerFactory.getLogger(MapRepository.class);

    private final Map<Long, User> users = new HashMap<>();

    @Override
    public void create(long id, String userName, UserStatus userStatus) {
        if (users.containsKey(id)) {
            return;
        }
        users.put(id, createUser(id, userName, userStatus));
    }


    @Override
    public List<User> readAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public boolean delete(long id) {
        if (users.containsKey(id)) {
            users.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public User read(long id) {
        return users.get(id);
    }

    @Override
    public User read(String phone) {
        for (Map.Entry<Long, User> entry : users.entrySet()) {
            String phoneUser = entry.getValue().getPhone();
            if (phoneUser.equals(phone)) {
                log.warn("user is found");
                return entry.getValue();
            }
        }
        throw new RuntimeException("user not found in MockServer");
    }

    public User createUser(long id, String userName, UserStatus userStatus) {
        return User.builder().chatId(id).userName(userName).userStatus(userStatus).build();
    }
}
