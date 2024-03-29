package pl.swiderski.app.controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import pl.swiderski.app.util.AlertMaking;
import pl.swiderski.app.util.LoginUser;
import pl.swiderski.dao.UserDao;
import pl.swiderski.model.User;

public class AccountSettingsController {

    private UserDao userDao = new UserDao();
    private AlertMaking alertMaking = new AlertMaking();

    @FXML
    private PasswordField oldPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField newPasswordConf;

    @FXML
    private JFXTextField newEmail;

    @FXML
    private JFXTextField newEmailConf;

    @FXML
    private JFXTextField accountName;

    @FXML
    private JFXTextField accountSurname;

    @FXML
    private JFXTextField accountLogin;

    @FXML
    private JFXTextField accountEmail;


    public void initialize() {
        fillProfileData();
    }


    @FXML
    void changeEmailButton() {
        int loginUserId = LoginUser.getUser().getID();
        if (isTypedEmailEquals()) {
            userDao.editEmail(loginUserId, newEmail.getText());
            fillProfileData();
            alertMaking.showInfoAlert("Email zostal zmieniony");
        } else {
            alertMaking.showErrorAlert("Email potwierdzajacy nie jest taki sam", "Error!!!");
        }
    }


    @FXML
    void changePasswordButton() {
        if (isTypedPassEquals()) {
            updatePassword();
        } else {
            alertMaking.showErrorAlert("Hasla nie sa takie same", "Blad");
        }
    }

    private boolean isTypedPassEquals() {
        return newPassword.getText().equals(newPasswordConf.getText());
    }

    private void updatePassword() {
        int loginUserId = LoginUser.getUser().getID();
        String newPass = newPassword.getText();
        String oldPass = oldPassword.getText();
        if (hasChangePass(loginUserId, newPass, oldPass)) {
            new AlertMaking().showInfoAlert("Zmieniono hasło");
        } else {
            new AlertMaking().showErrorAlert("Haslo nie zmienione, sprawdz hasla", "Error");
        }
    }


    private boolean hasChangePass(int loginUserId, String newPass, String oldPass) {
        return userDao.editPassword(loginUserId, oldPass, newPass);
    }


    private boolean isTypedEmailEquals() {
        return newEmail.getText().equals(newEmailConf.getText());
    }



    private void fillProfileData() {
        User user = userDao.findById(LoginUser.getUser().getID());
        accountName.setText(user.getFirstName());
        accountSurname.setText(user.getLastName());
        accountLogin.setText(user.getLogin());
        accountEmail.setText(user.getEmail());
    }

}
