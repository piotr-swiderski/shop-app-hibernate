package pl.swiderski.dao;

import org.hibernate.Session;
import pl.swiderski.model.Cart;

import java.util.List;

public class CartDao implements  QueryServiceDao<Cart>, CommandServiceDao<Cart> {

    @Override
    public void createNew(Cart cart) {
        Session session = openSession();
        session.beginTransaction();
        session.getTransaction();
        try {
            session.save(cart);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }
    }

    @Override
    public void edit(int ID, Cart cart) {

    }

    @Override
    public void delete(int ID) {

    }

    @Override
    public List<Cart> findAll() {
        return null;
    }

    @Override
    public Cart findById(int id) {
        return null;
    }
}
