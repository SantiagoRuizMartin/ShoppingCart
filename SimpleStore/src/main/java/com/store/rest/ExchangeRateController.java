package com.store.rest;

import com.store.control.ExchangeRateManager;
import com.store.exchangeservvice.ExchangeThreatManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExchangeRateController {

    @Autowired
    private ExchangeRateManager exchangeRateManager;

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/exchange_rate",method = RequestMethod.GET)
    public Double getExchangeRate(){
        return exchangeRateManager.getExchangeRate();
    }


}
