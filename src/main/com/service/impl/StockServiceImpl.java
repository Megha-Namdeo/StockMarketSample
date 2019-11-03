package main.com.service.impl;

import main.com.dao.StockDao;
import main.com.dao.impl.StockDaoImpl;
import main.com.model.Stock;
import main.com.model.StockType;
import main.com.service.StockService;

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
	public void calculateStockDividendYield(String stockSymbol, String stockPrice) throws Exception {

		System.out.println("Calculating dividend yield ");

		Stock stock = getStockFromMemory(stockSymbol.toUpperCase());

		if (stock == null) {

			throw new Exception("Stock not found");

		}

		double price = Double.parseDouble(stockPrice);

		if (StockType.PREFERRED.equals(stock.getType())) {

			double result = (stock.getFixedDividend() * stock.getParValue()) / price;

			System.out.println("Stock Dividend Yield ::" + roundOff(result));

		} else {

			System.out.println("Stock Dividend Yield :: " + roundOff(stock.getLastDividend() / price));

		}

	}

	@Override
	public Stock getStockFromMemory(String stockSymbol) {

		return stockDao.getStock(stockSymbol);
	}

	@Override
	public void calculateStockPERatio(String stockSymbol, String stockPrice) throws Exception {

		System.out.println("Calculating P/E");

		Stock stock = getStockFromMemory(stockSymbol.toUpperCase());

		if (stock == null) {

			throw new Exception("Stock not found");

		}

		double price = Double.parseDouble(stockPrice);

		if (!(stock.getLastDividend() <= 0)) {

			System.out.println("Stock P/E Ratio :: " + roundOff(price / stock.getLastDividend()));

		} else {

			System.out.println("Stock P/E Ratio :: " + "0.0");

		}

	}

	private static double roundOff(double value) {

		return (double) Math.round(value * 100) / 100;

	}

}
