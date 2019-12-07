package pl.swiderski.app.util;

import pl.swiderski.dao.UserDao;
import pl.swiderski.model.User;

public class LoginUser {

    private static int id = -1;
    private static UserDao userDao = new UserDao();

    public static User getUser() {
        if (id != -1) {
            return userDao.findById(id);
        } else {
            return new User();
        }
    }


    public static void setUserID(int userID) {
        id = userID;
    }


    public static void logoutUser() {
        id = -1;
    }
}

