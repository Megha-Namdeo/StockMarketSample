package main.com.dao;

import java.util.List;

import main.com.model.Trade;

public interface TradeDao {

	void addTrade(String stockSymbol, List<Trade> tradeList);
	
	List<Trade> getAllTrades(String stockSymbol);

	List<Trade> getTradesInFifteenMinute(String stockSymbol);

	
}
