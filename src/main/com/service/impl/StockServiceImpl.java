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

		// System.out.println(stock.toString());

		if (StockType.PREFERRED.equals(stock.getType())) {

			System.out.println("StockDividendYield ::" + (stock.getFixedDividend() * stock.getParValue()) / price);

		} else {

			System.out.println("StockDividendYield :: " + stock.getLastDividend() / price);

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

			System.out.println("StockP/ERatio :: " + price / stock.getLastDividend());

		} else {

			System.out.println("StockP/ERatio :: " + "0.0");

		}

	}

}
