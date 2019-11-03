package com.stock.service.impl;

import java.util.Calendar;
import java.util.List;

import com.stock.dao.TradeDao;
import com.stock.dao.impl.TradeDaoImpl;
import com.stock.model.Stock;
import com.stock.model.Trade;
import com.stock.model.TradeType;
import com.stock.service.StockService;
import com.stock.service.TradeService;

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
	public void addTrade(String stockSymbol, int quantity, TradeType indicator, double stockPrice) throws Exception {

		System.out.println("Storing trade values");

		Stock stock = stockService.getStockFromMemory(stockSymbol.toUpperCase());

		if (stock == null) {

			throw new Exception("Stock not found");

		}

		List<Trade> tradeList = tradeDao.getAllTrades(stock.getSymbol());

		if (tradeList == null || tradeList.isEmpty()) {

			tradeDao.addTrade(stock.getSymbol(),
					asList(new Trade(stock, Calendar.getInstance().getTime(), quantity, indicator, stockPrice)));

		} else {

			tradeList.add(new Trade(stock, Calendar.getInstance().getTime(), quantity, indicator, stockPrice));

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

			System.out.println("Volume Weighted Stock Price based on trades in past 15 minutes: " + roundOff(result));

		} else {

			System.out.println("Trade is empty for :" + stock.getSymbol());

		}

	}

	@Override
	public void calculateGBCE() throws Exception {

		System.out.println("Calculating GBCE for all Stock");

		List<Trade> tradeList = tradeDao.getAllTrades();

		if (tradeList != null && !tradeList.isEmpty()) {

			double totalPrice = 1;

			for (Trade trade : tradeList) {

				totalPrice *= trade.getPrice();

			}

			double result = Math.pow(totalPrice, (1.0 / tradeList.size()));

			System.out.println("GBCE for all stocks present " + roundOff(result));

		} else {

			System.out.println("TradeList is empty - cannot calculate GBCE");

		}

	}

	private static double roundOff(double value) {

		return (double) Math.round(value * 100) / 100;

	}

}
