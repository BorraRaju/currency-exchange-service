package com.training.practise;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.practise.bean.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

	ExchangeValue findByFromAndTo(String from, String to);
}
