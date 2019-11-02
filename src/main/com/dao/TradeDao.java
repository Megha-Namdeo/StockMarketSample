package main.com.dao;

import java.util.List;

import main.com.model.Stock;
import main.com.model.Trade;

public interface TradeDao {

	void addTrade(Trade trade);
	
	List<Trade> getAllTrades();

	List<Trade> getTrades(Stock stock, int lastMinutes);

	
}
