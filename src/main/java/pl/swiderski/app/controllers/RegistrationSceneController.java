package pl.swiderski.app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pl.swiderski.app.util.AlertMaking;
import pl.swiderski.app.util.ScreenChanger;
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


    @FXML
    public void clickBack() {
        try {
            new ScreenChanger().change(getClass().getResource("/fxml/loginScreen.fxml"), root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void clickRegistration() {
        if (isPassAndConfPassEquals()) {
            User user = new User(
                    firstName.getText(),
                    secondName.getText(),
                    email.getText(),
                    login.getText(),
                    password.getText());
            userDao.createNew(user);
            clickBack();
        } else {
            new AlertMaking().showErrorAlert("Podałes nie poprawne hasło lub login jest juz zajęty! ","Blad");
        }
    }


    private boolean isPassAndConfPassEquals() {
        return password.getText().equals(confirmPassword.getText()) && userDao.checkAvailabilityLogin(login.getText());
    }


}
