package main.com.dao.impl;

import java.util.HashMap;
import java.util.Map;

import main.com.dao.StockDao;
import main.com.model.Stock;
import main.com.model.StockType;

public class StockDaoImpl implements StockDao {

	private static Map<String, Stock> stockData = new HashMap<>();

	{
		stockData.put("TEA", new Stock("TEA", StockType.COMMON, 0, 0, 100));
		stockData.put("POP", new Stock("POP", StockType.COMMON, 8, 0, 100));
		stockData.put("ALE", new Stock("ALE", StockType.COMMON, 23, 0, 60));
		stockData.put("GIN", new Stock("GIN", StockType.PREFERRED, 8, 2, 100));
		stockData.put("JOE", new Stock("JOE", StockType.COMMON, 13, 0, 250));
	}

	private static StockDaoImpl instance = null;

	public static StockDao getInstance() {

		if (instance == null) {

			instance = new StockDaoImpl();

		}

		return instance;
	}

	@Override
	public Stock getStock(String symbol) {

		return stockData.get(symbol);

	}

}
