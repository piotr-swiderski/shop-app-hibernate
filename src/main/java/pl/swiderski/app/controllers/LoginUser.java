package pl.swiderski.app.controllers;

import pl.swiderski.model.User;

public class LoginUser {

    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        LoginUser.user = user;
    }
}
