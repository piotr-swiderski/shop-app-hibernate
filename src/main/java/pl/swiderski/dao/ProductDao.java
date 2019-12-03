package pl.swiderski.dao;

import pl.swiderski.model.Product;

import java.util.List;

public class ProductDao implements QueryServiceDao<Product>, CommandServiceDao<Product> {



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
        return openSession().createQuery("From Product",Product.class).getResultList();
    }

    @Override
    public Product findById(int id) {
        return null;
    }
}
