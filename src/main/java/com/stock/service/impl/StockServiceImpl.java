package com.stock.service.impl;

import com.stock.dao.StockDao;
import com.stock.dao.impl.StockDaoImpl;
import com.stock.model.Stock;
import com.stock.model.StockType;
import com.stock.service.StockService;

public class StockServiceImpl implements StockService {

	private static StockDao stockDao = StockDaoImpl.getInstance();

	private static StockServiceImpl instance = null;

	public static StockService getInstance() {

		if (instance == null) {

			instance = new StockServiceImpl();

		}

		return instance;
	}

	@Override
	public void calculateStockDividendYield(String stockSymbol, double stockPrice) throws Exception {

		System.out.println("Calculating dividend yield ");

		Stock stock = getStockFromMemory(stockSymbol.toUpperCase());

		if (stock == null) {

			throw new Exception("Stock not found");

		}

		if (StockType.PREFERRED.equals(stock.getType())) {

			double result = (stock.getFixedDividend() * stock.getParValue()) / stockPrice;

			System.out.println("Stock Dividend Yield ::" + roundOff(result));

		} else {

			System.out.println("Stock Dividend Yield :: " + roundOff(stock.getLastDividend() / stockPrice));

		}

	}

	@Override
	public Stock getStockFromMemory(String stockSymbol) {

		return stockDao.getStock(stockSymbol);
	}

	@Override
	public void calculateStockPERatio(String stockSymbol, double stockPrice) throws Exception {

		System.out.println("Calculating P/E");

		Stock stock = getStockFromMemory(stockSymbol.toUpperCase());

		if (stock == null) {

			throw new Exception("Stock not found");

		}

		if (!(stock.getLastDividend() <= 0)) {

			System.out.println("Stock P/E Ratio :: " + roundOff(stockPrice / stock.getLastDividend()));

		} else {

			System.out.println("Stock P/E Ratio :: " + "0.0");

		}

	}

	private static double roundOff(double value) {

		return (double) Math.round(value * 100) / 100;

	}

}
