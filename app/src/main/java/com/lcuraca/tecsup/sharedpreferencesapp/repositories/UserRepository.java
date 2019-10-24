package com.lcuraca.tecsup.sharedpreferencesapp.repositories;

import com.lcuraca.tecsup.sharedpreferencesapp.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(100, "ebenites", "tecsup", "Erick Benites"));
        users.add(new User(200, "jfarfan", "tecsup", "Jaime Farfán"));
        users.add(new User(300, "drodriguez", "tecsup", "David Rodriguez"));
    }

    public static User login(String username, String passwrord) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(passwrord)) {
                return user;
            }
        }
        return null;
    }

    public static User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
