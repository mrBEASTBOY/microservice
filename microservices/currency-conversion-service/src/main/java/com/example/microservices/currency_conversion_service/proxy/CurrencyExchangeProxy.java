package com.example.microservices.currency_conversion_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.microservices.currency_conversion_service.bean.CurrencyConversion;

@FeignClient(name="currency-exchange")
public interface CurrencyExchangeProxy {
	// Copy the method that we want to 
	// call from other service (the method 
	// signature must be same
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	  public CurrencyConversion retrieveExchangeValue(
	    @PathVariable String from,
	    @PathVariable String to
	  );
}

