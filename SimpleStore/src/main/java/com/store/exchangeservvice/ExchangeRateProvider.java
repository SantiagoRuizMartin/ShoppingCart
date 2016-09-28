package com.store.exchangeservvice;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExchangeRateProvider {

    public String getExchangeRate(){

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml",
                String.class);
    }

}
