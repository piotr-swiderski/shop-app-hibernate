package pl.swiderski.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import pl.swiderski.model.Product;

import java.util.List;

public class ProductDao implements QueryServiceDao<Product>, CommandServiceDao<Product>, ProductQueryService {


    @Override
    public void createNew(Product product) {
        Session session = openSession();
        session.beginTransaction();
        try {
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            ;
        }
    }


    @Override
    public void edit(Product product) {
        Session session = openSession();
        session.beginTransaction();
        Query query = session.createQuery(
                "Update Product AS p set " +
                        "p.name=:name, " +
                        "p.serial=:serial, " +
                        "p.category=:category, " +
                        "p.quantity=:quantity, " +
                        "p.price=:price " +
                        "where p.id=:id");
        query.setParameter("name", product.getName());
        query.setParameter("serial", product.getSerial());
        query.setParameter("category", product.getCategory());
        query.setParameter("quantity", product.getQuantity());
        query.setParameter("price", product.getPrice());
        query.setParameter("id", product.getID());
        try {
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int ID) {
        Session session = openSession();
        session.beginTransaction();
        try {
            Query query = session.createQuery("Delete Product AS p WHERE p.id=:id");
            query.setParameter("id", ID);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

    }

    @Override
    public List<Product> findAll() {
        return openSession().createQuery("From Product", Product.class).getResultList();
    }

    @Override
    public Product findById(int id) {
        return null;
    }


    @Override
    public List<Product> findByPriceLimit(Double min, Double max) {
        Query<Product> query = openSession().createQuery("FROM Product where price between :min and :max ", Product.class);
        query.setParameter("min", min);
        query.setParameter("max", max);
        return query.getResultList();

    }

    @Override
    public List<Product> findByCategory(List<String> categories) {
        Query<Product> query = openSession().createQuery("From Product where category in(:list)", Product.class);
        query.setParameter("list", categories);
        return query.getResultList();

    }

    @Override
    public List<Product> findByName(String text) {
        Query<Product> query = openSession().createQuery("From Product where name like :text", Product.class);
        query.setParameter("text", "%" + text + "%");
        return query.getResultList();
    }

    public void decrementQuantity(int id, int quantity) {
        Session session = openSession();
        session.beginTransaction();
        Query query = session.createQuery("Update Product p set p.quantity = p.quantity - :quantity where p.id=:id");
        query.setParameter("quantity", quantity);
        query.setParameter("id", id);
        try {
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }
}
