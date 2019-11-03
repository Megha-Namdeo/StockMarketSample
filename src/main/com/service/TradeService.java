package main.com.service;

public interface TradeService {

	void addTrade(String stockSymbol, String quantity, String indicator, String stockPrice) throws Exception;

	void calculateStockPriceTradeMinutes(String stockSymbol) throws Exception;

	void calculateGBCE() throws Exception;

}
