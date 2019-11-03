package com.stock.service;

import com.stock.model.Stock;

public interface StockService {

	void calculateStockDividendYield(String stockSymbol, double stockPrice) throws Exception;

	void calculateStockPERatio(String stockSymbol, double stockPrice) throws Exception;

	Stock getStockFromMemory(String stockSymbol);

}
