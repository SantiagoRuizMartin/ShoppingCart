package com.store.dao;


import com.store.model.Product;

import java.util.List;

public interface AvailableProductsDao {

    List<Product> getAvailableProductsForCustomer(Integer customerId);
    void deleteProductsFor(Integer customerId);
    void addProductForCustomer(Integer customer,Integer productId);

}
