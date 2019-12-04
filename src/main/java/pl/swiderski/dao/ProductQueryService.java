package pl.swiderski.dao;

import pl.swiderski.model.Product;

import java.util.List;

public interface ProductQueryService {

    List<Product> findByPriceLimit(Double min, Double max);
    List<Product> findByCategory(List<String> categories);
    List<Product> findByName(String text);
}
