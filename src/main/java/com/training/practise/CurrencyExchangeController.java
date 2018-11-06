package com.training.practise;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.training.practise.bean.ExchangeValue;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValues(@PathVariable String from, @PathVariable String to) {
		
		/*ExchangeValue exchangeValue= new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65)) ;
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		*/
		ExchangeValue exchangeValue = new ExchangeValue();/* = new ExchangeValue(1004L, "SNG", "IND", BigDecimal.valueOf(50)) */;
		exchangeValue.setId(Long.parseLong("1004"));
		exchangeValue.setFrom("SNG");
		exchangeValue.setTo("IND");
		exchangeValue.setConversionMultiple(BigDecimal.valueOf(50));
		exchangeValueRepository.save(exchangeValue);
		
		//retreive the data
		exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		logger.info("CurrencyExchangeController exchangeValue{} : ",exchangeValue);
		
		return exchangeValue;
	}		
}
