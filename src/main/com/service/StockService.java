package main.com.service;

import main.com.model.Stock;

public interface StockService {

	void calculateStockDividendYield(String stockSymbol, String stockPrice) throws Exception;

	void calculateStockPERatio(String stockSymbol, String stockPrice) throws Exception;

	Stock getStockFromMemory(String stockSymbol);

}
