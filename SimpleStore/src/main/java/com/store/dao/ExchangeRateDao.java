package com.store.dao;


public interface ExchangeRateDao {
    void saveExchangeRate(Double exchangeRate);
    Double getActualExchangeRate();
}
