package com.example.microservices.currency_exchange_service.repository;

import com.example.microservices.currency_exchange_service.bean.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository
  extends JpaRepository<CurrencyExchange, Long> {
  public CurrencyExchange findByFromAndTo(String from, String to);
}
