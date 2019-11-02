package main.com.dao;

import main.com.model.Stock;

public interface StockDao {

	Stock getStock(String symbol);
}
