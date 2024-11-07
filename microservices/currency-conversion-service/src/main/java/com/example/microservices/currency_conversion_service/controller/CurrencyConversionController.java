package com.example.microservices.currency_conversion_service.controller;

import com.example.microservices.currency_conversion_service.bean.CurrencyConversion;
import com.example.microservices.currency_conversion_service.proxy.CurrencyExchangeProxy;
import java.math.BigDecimal;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
  @Autowired
  private CurrencyExchangeProxy proxy;

  @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversion(
    @PathVariable String from,
    @PathVariable String to,
    @PathVariable BigDecimal quantity
  ) {
    HashMap<String, String> uriVariables = new HashMap<String, String>();
    uriVariables.put("from", from);
    uriVariables.put("to", to);

    ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate()
    .getForEntity(
        "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
        CurrencyConversion.class,
        uriVariables
      );

    // mapping
    CurrencyConversion currencyConversion = responseEntity.getBody();

    return new CurrencyConversion(
      currencyConversion.getId(),
      from,
      to,
      quantity,
      currencyConversion.getConversionMultiple(),
      quantity.multiply(currencyConversion.getConversionMultiple()),
      currencyConversion.getEnvironment()
    );
  }

  @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversionFeign(
    @PathVariable String from,
    @PathVariable String to,
    @PathVariable BigDecimal quantity
  ) {
    // mapping
    CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);

    return new CurrencyConversion(
      currencyConversion.getId(),
      from,
      to,
      quantity,
      currencyConversion.getConversionMultiple(),
      quantity.multiply(currencyConversion.getConversionMultiple()),
      currencyConversion.getEnvironment()
    );
  }
}
