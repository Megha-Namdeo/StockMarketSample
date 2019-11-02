package main.com.service.impl;

import java.util.Calendar;

import main.com.dao.TradeDao;
import main.com.dao.impl.TradeDaoImpl;
import main.com.model.Stock;
import main.com.model.Trade;
import main.com.model.TradeType;
import main.com.service.StockService;
import main.com.service.TradeService;

public class TradeServiceImpl implements TradeService {

	private static StockService stockService = StockServiceImpl.getInstance();

	private static TradeDao tradeDao = TradeDaoImpl.getInstance();

	private static TradeServiceImpl instance = null;

	public static TradeService getInstance() {

		if (instance == null) {

			instance = new TradeServiceImpl();

		}

		return instance;
	}

	@Override
	public void addTrade(String stockSymbol, String quantity, String indicator, String stockPrice) throws Exception {
		
		System.out.println("Storing trade values");

		Stock stock = stockService.getStockFromMemory(stockSymbol.toUpperCase());

		if (stock == null) {

			throw new Exception("Stock not found");

		}

		double price = Double.parseDouble(stockPrice);

		int number = Integer.parseInt(quantity);

		TradeType tradeType = TradeType.valueOf(indicator.toUpperCase());

		tradeDao.addTrade(new Trade(stock, Calendar.getInstance().getTime(), number, tradeType, price));

		System.out.println("Trade has been stored for stock " + stock.getSymbol());

	}

}
