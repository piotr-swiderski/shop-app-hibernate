package pl.swiderski.dao;

import org.hibernate.query.Query;
import pl.swiderski.model.Product;

import java.util.List;

public class ProductDao implements QueryServiceDao<Product>, CommandServiceDao<Product>, ProductQueryService {


    @Override
    public void createNew(Product product) {

    }

    @Override
    public void edit(int ID, Product product) {

    }

    @Override
    public void delete(int ID) {

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
}
