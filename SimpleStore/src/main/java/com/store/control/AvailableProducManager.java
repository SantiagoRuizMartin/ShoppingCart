package com.store.control;


import com.store.dao.AvailableProductsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AvailableProducManager {

    @Autowired
    private AvailableProductsDao availableProductsDao;



    public void setAvailableProductsForCustomer(Integer customerId, List<Integer> productIdList){

        deleteAllAvailableProductsFor(customerId);
        for (Integer productId:productIdList){
            addProduct(customerId,productId);
        }
    }

    private void deleteAllAvailableProductsFor(Integer customerId) {
        availableProductsDao.deleteProductsFor(customerId);
    }

    private void addProduct(Integer customerId, Integer productId) {
        availableProductsDao.addProductForCustomer(customerId,productId);
    }

}
