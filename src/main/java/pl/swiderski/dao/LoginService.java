package pl.swiderski.dao;

import org.hibernate.Session;
import pl.swiderski.DataBaseConnectorFactory;
import pl.swiderski.model.User;

public interface LoginService {

    boolean checkLoginAndPass(String login, String pass);

    void editPassword(String login, String oldPass, String NewPass);

    boolean checkAvailabilityLogin(String login);

    User getUserByLogin(String login);

   // default Session openSession(){
   //      return DataBaseConnectorFactory.getFactory().openSession();
   // }

}
