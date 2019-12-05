package pl.swiderski.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pl.swiderski.app.AlertMaking;
import pl.swiderski.app.ScreenChanger;
import pl.swiderski.dao.UserDao;
import pl.swiderski.model.User;

import java.io.IOException;

public class RegistrationSceneController {

    
    UserDao userDao = new UserDao();

    @FXML
    TextField firstName;
    @FXML
    TextField secondName;
    @FXML
    TextField email;
    @FXML
    TextField login;
    @FXML
    PasswordField password;
    @FXML
    PasswordField confirmPassword;
    @FXML
    AnchorPane root;


    public void clickBack() {
        try {
            new ScreenChanger().change(getClass().getResource("/fxml/loginScreen.fxml"), root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickRegistration() {
        if (password.getText().equals(confirmPassword.getText()) && userDao.checkAvailabilityLogin(login.getText())) {
            User user = new User(
                    firstName.getText(),
                    secondName.getText(),
                    email.getText(),
                    login.getText(),
                    password.getText());

            userDao.createNew(user);

        } else {
            new AlertMaking().show("Podałes nie poprawne hasło lub login jest juz zajęty! ","Blad");
        }

    }




}
