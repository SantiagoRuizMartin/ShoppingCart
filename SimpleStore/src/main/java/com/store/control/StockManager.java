package com.store.control;


import com.store.dao.ProductDao;
import com.store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class StockManager {

    @Autowired
    private ProductDao productDao;

    public List<Product> getProductsInStock(Integer customerId){

        List<Product> productList = queryForProductsInStock();
        productList  = setCurrentValueInDollar(productList);
        productList = setCurrentValueInEuro(productList);

        return productList;
    }

    private List<Product> queryForProductsInStock() {

        return null;
    }

    private List<Product>  setCurrentValueInDollar(List<Product> productList) {

        return null;
    }

    private List<Product> setCurrentValueInEuro(List<Product> productList) {

        return null;
    }

    public List<Product> getProduct(String id) {
        List<Product> productList = queryForProducts(id);
        productList = setCurrentValueInDollar(productList);
        productList = setCurrentValueInEuro(productList);
        return productList;
    }

    private List<Product> queryForProducts(String id) {
        return null;
    }

    public List<Product> getProduct(Integer id, Integer customerId) {
        return null;
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
}
