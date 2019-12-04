package pl.swiderski.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.swiderski.model.User;
import pl.swiderski.util.Hash;

import javax.jws.soap.SOAPBinding;
import java.net.UnknownServiceException;
import java.util.List;

public class UserDao implements LoginService, CommandServiceDao<User>, QueryServiceDao<User> {


    @Override
    public void createNew(User user) {
        Session session = openSession();
        session.beginTransaction();
        try {
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void edit(int ID, User user) {

    }

    @Override
    public void delete(int ID) {

    }

    @Override
    public boolean checkLoginAndPass(String login, String pass) {
        Query<User> query = openSession().createQuery("FROM User u WHERE u.login=:u_login and u.password =:u_pass", User.class);
        query.setParameter("u_login", login);
        query.setParameter("u_pass", new Hash(pass).hash()); //"sha2('"+pass+"',512)");
        if (query.list().size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void editPassword(String login, String oldPass, String NewPass) {

    }

    @Override
    public boolean checkAvailabilityLogin(String login) {
        Query query = openSession().createQuery("FROM User u WHERE u.login=:u_login");
        query.setParameter("u_login", login);
        if (query.getResultList().size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUserByLogin(String login) {
        Query<User> query = openSession().createQuery("FROM User u WHERE u.login=:u_login",User.class);
        query.setParameter("u_login", login);
        return query.getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return openSession().createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public User findById(int id) {
        Query query = openSession().createQuery("FROM User u WHERE u.id=:uid");
        query.setParameter("uid", id);
        User user = (User) query.getSingleResult();
        if (user != null) {
            return user;
        } else {
            return new User();
        }

    }
}
