package com.store.rest;


import com.store.control.AvailableProducManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class AvailableProducController {

    @Autowired
    private AvailableProducManager availableProducManager;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/availableProducts",method = RequestMethod.POST)
    public void  setAvailableProducts(@RequestParam(value = "customerId") Integer customerId,
                                      @RequestBody List<Integer> productsId){
        availableProducManager.setAvailableProductsForCustomer(customerId,productsId);
    }
}
