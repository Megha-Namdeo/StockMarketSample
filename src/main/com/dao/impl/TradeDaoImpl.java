package main.com.dao.impl;

import java.util.List;

import main.com.dao.TradeDao;
import main.com.model.Stock;
import main.com.model.Trade;

public class TradeDaoImpl implements TradeDao {

	private static TradeDaoImpl instance = null;

	public static TradeDao getInstance() {

		if (instance == null) {

			instance = new TradeDaoImpl();

		}

		return instance;
	}

	@Override
	public void addTrade(Trade trade) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Trade> getAllTrades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trade> getTrades(Stock stock, int lastMinutes) {
		// TODO Auto-generated method stub
		return null;
	}

}
