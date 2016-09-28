package com.store.dao;


import com.store.model.Product;

import java.util.List;

public interface ProductDao {

    Product getProduct(Integer id);
    List<Product> getAllProducts();

}
