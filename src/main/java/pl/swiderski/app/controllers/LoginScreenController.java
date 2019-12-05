package pl.swiderski.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pl.swiderski.app.AlertMaking;
import pl.swiderski.app.ScreenChanger;
import pl.swiderski.dao.UserDao;
import pl.swiderski.model.User;

import java.io.IOException;

public class LoginScreenController {

    @FXML
    TextField login;
    @FXML
    PasswordField password;
    @FXML
    AnchorPane root;

    public void clickLogin() {
        if (new UserDao().checkLoginAndPass(login.getText(), password.getText())) {
            try {
                LoginUser.setUser(new UserDao().getUserByLogin(login.getText()));
                new ScreenChanger().change(getClass().getResource("/fxml/productListScene.fxml"), root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            new AlertMaking().show("Nie prawidlowe dane logowania", "Error");
        }
    }

    public void clickRegistration() {
        try {
            new ScreenChanger().change(getClass().getResource("/fxml/registrationScene.fxml"), root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
