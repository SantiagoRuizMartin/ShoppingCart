package com.store.control;

import com.store.dao.ExchangeRateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class ExchangeRateManager {


    @Autowired
    private ExchangeRateDao exchangeRateDao;

    public Double getExchangeRate() {
        return exchangeRateDao.getActualExchangeRate();
    }

    public Double getExchangeRateFomXMl(String xml, String currencyId) {


        Double rate = 0.0;
        if (!xml.equals("")){
            Scanner s = new Scanner(xml);
            String nextString;
            while (s.hasNext()){
                nextString = s.next();
                if(nextString.contains(currencyId)){
                    return getExchange(s.next());
                }
            }
        }
        return rate;
    }

    private Double getExchange(String next) {

        next = next.replace("'"," ");
        next = next.replace("/>","");
        next = next.replace("rate= ","");
        return Double.parseDouble(next);
    }
}
