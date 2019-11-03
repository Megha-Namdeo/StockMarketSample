package com.stock.service;

import com.stock.exception.StockNotFound;
import com.stock.model.Stock;

public interface StockService {

	void calculateStockDividendYield(String stockSymbol, double stockPrice) throws StockNotFound;

	void calculateStockPERatio(String stockSymbol, double stockPrice) throws StockNotFound;

	Stock getStockFromMemory(String stockSymbol);

}
