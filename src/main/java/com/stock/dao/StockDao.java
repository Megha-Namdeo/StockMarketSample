package com.stock.dao;

import com.stock.model.Stock;

public interface StockDao {

	Stock getStock(String symbol);
}
