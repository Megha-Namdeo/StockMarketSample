package com.stock.service;

import com.stock.exception.StockNotFound;
import com.stock.exception.TradeNotFound;
import com.stock.model.TradeType;

public interface TradeService {

	void addTrade(String stockSymbol, int quantity, TradeType indicator, double stockPrice) throws StockNotFound;

	void calculateStockPriceTradeMinutes(String stockSymbol) throws StockNotFound, TradeNotFound;

	void calculateGBCE();

}
