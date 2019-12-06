package pl.swiderski.dao;

import org.hibernate.Session;
import pl.swiderski.DataBaseConnectorFactory;
import pl.swiderski.model.User;

public interface LoginService {

    boolean checkLoginAndPass(String login, String pass);

    boolean editPassword(int ID, String oldPass, String NewPass);

    boolean checkAvailabilityLogin(String login);

    User getUserByLogin(String login);

    User getUserByID(int id);

    void editEmail(int id, String newEmail);

   // default Session openSession(){
   //      return DataBaseConnectorFactory.getFactory().openSession();
   // }

}
