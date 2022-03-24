package com.example.restapi.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int lastUserId = 3;


    static {
        users.add(new User(1, "name", new Date()));
        users.add(new User(2, "name2", new Date()));
        users.add(new User(3, "name3", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if(user.getId() == null) user.setId(++lastUserId);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for (User user: users) {
            if (user.getId() == id) return user;
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iter = users.iterator();
        while(iter.hasNext()) {
            User user = iter.next();

            if(user.getId() == id) {
                iter.remove();
                return user;
            }
        }
        return null;
    }
}
