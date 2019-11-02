package main.com.service;

public interface TradeService {

	void addTrade(String stockSymbol, String quantity, String indicator, String stockPrice) throws Exception;

}
