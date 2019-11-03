package com.stock.service;

import com.stock.model.TradeType;

public interface TradeService {

	void addTrade(String stockSymbol, int quantity, TradeType indicator, double stockPrice) throws Exception;

	void calculateStockPriceTradeMinutes(String stockSymbol) throws Exception;

	void calculateGBCE() throws Exception;

}
