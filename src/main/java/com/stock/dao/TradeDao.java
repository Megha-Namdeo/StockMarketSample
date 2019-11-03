package com.stock.dao;

import java.util.List;

import com.stock.model.Trade;

public interface TradeDao {

	void addTrade(String stockSymbol, List<Trade> tradeList);
	
	List<Trade> getAllTrades(String stockSymbol);

	List<Trade> getTradesInFifteenMinute(String stockSymbol);

	List<Trade> getAllTrades();

	
}
