package com.store.exchangeservvice;


import com.store.control.ExchangeRateManager;
import com.store.dao.ExchangeRateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeThreatManager {

    @Autowired
    private ExchangeRateManager exchangeRateManager;
    @Autowired
    private ExchangeRateDao exchangeRateDao;
    @Autowired
    private ExchangeRateProvider exchangeRateProvider;
    private ExchangeRateUpdater exchangeRateUpdater;
    public void startTread(){
        exchangeRateUpdater = new ExchangeRateUpdater();
        exchangeRateUpdater.run();
    }

    public class ExchangeRateUpdater extends Thread {

        @Override
        public void run() {
            while (true){
                try {
                    Double rate = exchangeRateManager.getExchangeRateFomXMl(exchangeRateProvider.getExchangeRate(),"USD");
                    exchangeRateDao.saveExchangeRate(Double.parseDouble(rate+""));
                    super.sleep(60*1000*60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
