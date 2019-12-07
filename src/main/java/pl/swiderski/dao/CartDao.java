package pl.swiderski.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.swiderski.model.Cart;
import pl.swiderski.model.Product;
import pl.swiderski.model.User;

import java.util.ArrayList;
import java.util.List;

public class CartDao implements QueryServiceDao<Cart>, CommandServiceDao<Cart> {

    @Override
    public void createNew(Cart cart) {
        Session session = openSession();
        session.beginTransaction();
        session.getTransaction();
        try {
            session.save(cart);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void edit(Cart cart) {

    }

    @Override
    public void delete(int ID) {

    }

    @Override
    public List<Cart> findAll() {
        return openSession().createQuery("FROM Cart", Cart.class).getResultList();
    }

    @Override
    public Cart findById(int id) {
        return null;
    }


    public List<Product> getListOfProductByIdCart(int id) {
        Session session = openSession();
        session.beginTransaction();
        Query query = session.createQuery("select p from Cart c join c.products p join c.user u where c.ID =:id");
        query.setParameter("id", id);
        try {
            session.getTransaction().commit();
            return query.list();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public List<Cart> findAllByUserID(int id) {
        Session session = openSession();
        session.beginTransaction();
        Query query = session.createQuery("Select c from Cart c join c.user u where u.id=:id");
        query.setParameter("id", id);
        return query.getResultList();

    }
}
