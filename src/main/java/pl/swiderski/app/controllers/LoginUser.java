package pl.swiderski.app.controllers;

import pl.swiderski.dao.UserDao;
import pl.swiderski.model.User;

public class LoginUser {

    private static int id;
    private static UserDao userDao = new UserDao();

    public static User getUser() {
        return userDao.findById(id);
    }

    public static void setUserID(int userID) {
        id = userID;
    }
}
