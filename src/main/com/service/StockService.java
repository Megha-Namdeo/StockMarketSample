package main.com.service;


public interface StockService {

	void calculateStockDividendYield(String stockSymbol, String stockPrice) throws Exception;

	void calculateStockPERatio(String stockSymbol, String stockPrice) throws Exception;

}
