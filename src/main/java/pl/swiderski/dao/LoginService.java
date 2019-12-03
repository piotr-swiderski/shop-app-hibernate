package pl.swiderski.dao;

import org.hibernate.Session;
import pl.swiderski.DataBaseConnectorFactory;

public interface LoginService {

    boolean checkLoginAndPass(String login, String pass);

    void editPassword(String login, String oldPass, String NewPass);

    boolean CheckAvailabilityLogin(String login);

   // default Session openSession(){
   //      return DataBaseConnectorFactory.getFactory().openSession();
   // }

}
