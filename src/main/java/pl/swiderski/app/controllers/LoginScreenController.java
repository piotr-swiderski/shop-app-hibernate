package pl.swiderski.app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
        System.out.println(new UserDao().checkLoginAndPass(login.getText(), password.getText()));
    }

    public void clickRegistration() {
        try {
            new ScreenChanger().change(getClass().getResource("/fxml/registrationScene.fxml"), root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
