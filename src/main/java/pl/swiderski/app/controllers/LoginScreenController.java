package pl.swiderski.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pl.swiderski.app.util.AlertMaking;
import pl.swiderski.app.util.LoginUser;
import pl.swiderski.app.util.ScreenChanger;
import pl.swiderski.dao.UserDao;

import java.io.IOException;

public class LoginScreenController {

    private UserDao userDao = new UserDao();
    private ScreenChanger screenChanger = new ScreenChanger();
    private AlertMaking alertMaking = new AlertMaking();

    @FXML
    TextField login;
    @FXML
    PasswordField password;
    @FXML
    AnchorPane root;

    @FXML
    public void clickLogin() {
        if (userDao.checkLoginAndPass(login.getText(), password.getText())) {
            try {
                LoginUser.setUserID(new UserDao().getUserByLogin(login.getText()).getID());
                screenChanger.change(getClass().getResource("/fxml/mainScreen.fxml"), root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alertMaking.showErrorAlert("Nie prawidlowe dane logowania", "Error");
        }
    }

    @FXML
    public void clickRegistration() {
        try {
            screenChanger.change(getClass().getResource("/fxml/registrationScene.fxml"), root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
