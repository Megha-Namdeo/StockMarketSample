package main.com.service.impl;

import java.util.Calendar;
import java.util.List;

import main.com.dao.TradeDao;
import main.com.dao.impl.TradeDaoImpl;
import main.com.model.Stock;
import main.com.model.Trade;
import main.com.model.TradeType;
import main.com.service.StockService;
import main.com.service.TradeService;

import static java.util.Arrays.asList;

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

		List<Trade> tradeList = tradeDao.getAllTrades(stock.getSymbol());

		if (tradeList == null || tradeList.isEmpty()) {

			tradeDao.addTrade(stock.getSymbol(),
					asList(new Trade(stock, Calendar.getInstance().getTime(), number, tradeType, price)));

		} else {

			tradeList.add(new Trade(stock, Calendar.getInstance().getTime(), number, tradeType, price));

			tradeDao.addTrade(stock.getSymbol(), tradeList);
		}

		List<Trade> updatedTradeList = tradeDao.getAllTrades(stock.getSymbol());

		updatedTradeList.forEach(System.out::println);

		System.out.println("Trade has been stored for stock " + stock.getSymbol());

	}

	@Override
	public void calculateStockPriceTradeMinutes(String stockSymbol) throws Exception {

		System.out.println("Calculating Volume Weighted Stock Price based on trades in past 15 minutes");

		Stock stock = stockService.getStockFromMemory(stockSymbol.toUpperCase());

		if (stock == null) {

			throw new Exception("Stock not found");

		}

		List<Trade> tradeList = tradeDao.getTradesInFifteenMinute(stock.getSymbol());

		if (tradeList != null && !tradeList.isEmpty()) {

			double totalTradePrice = 0;

			int totalTradeQuantity = 0;

			for (Trade trade : tradeList) {

				totalTradePrice = totalTradePrice + (trade.getQuantity() * trade.getPrice());

				totalTradeQuantity = totalTradeQuantity + trade.getQuantity();

			}
			
			double result = totalTradePrice / totalTradeQuantity;

			System.out.println("Volume Weighted Stock Price based on trades in past 15 minutes: "
					+ result);

		} else {

			System.out.println("Trade is empty for :" + stock.getSymbol());

		}

	}

}
