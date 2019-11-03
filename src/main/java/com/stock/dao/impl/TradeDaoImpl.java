package com.stock.dao.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stock.dao.TradeDao;
import com.stock.exception.TradeNotFound;
import com.stock.model.Stock;
import com.stock.model.StockType;
import com.stock.model.Trade;
import com.stock.model.TradeType;

public class TradeDaoImpl implements TradeDao {

	private static TradeDaoImpl instance = null;

	private static Map<String, List<Trade>> tradeData = new HashMap<>();

	private static List<Trade> teaList = new ArrayList<>();

	{
		teaList.add(new Trade(new Stock("TEA", StockType.COMMON, 0, 0, 100), new Date(), 6, TradeType.BUY, 10D));
		teaList.add(new Trade(new Stock("TEA", StockType.COMMON, 0, 0, 100), new Date(), 10, TradeType.SELL, 20D));
		teaList.add(new Trade(new Stock("TEA", StockType.COMMON, 0, 0, 100), new Date(), 3, TradeType.BUY, 30D));
		tradeData.put("TEA", teaList);

	}

	public static TradeDao getInstance() {

		if (instance == null) {

			instance = new TradeDaoImpl();

		}

		return instance;
	}

	@Override
	public void addTrade(String stockSymbol, List<Trade> tradeList) {

		tradeData.put(stockSymbol, tradeList);

	}

	@Override
	public List<Trade> getAllTrades(String stockSymbol) {

		return tradeData.get(stockSymbol.toUpperCase());

	}

	@Override
	public List<Trade> getTradesInFifteenMinute(String stockSymbol) throws TradeNotFound {

		final int fifteenMinutes = 15 * 60 * 1000;

		List<Trade> tradeListInFifteenMinute = new ArrayList<>();

		List<Trade> tradeList = getAllTrades(stockSymbol);

		if (tradeList != null && !tradeList.isEmpty()) {

			for (Trade trade : tradeList) {

				LocalDateTime tradeDate = LocalDateTime.ofInstant(trade.getTimeStamp().toInstant(),
						ZoneId.systemDefault());

				LocalDateTime systemTime = LocalDateTime.now();

				long differenceInTime = Duration.between(tradeDate, systemTime).toMillis();

				if (differenceInTime <= fifteenMinutes) {

					tradeListInFifteenMinute.add(trade);
				}

			}
			
		} else {

			throw new TradeNotFound("No trade found for given Stock " + stockSymbol);
			
		}

		return tradeListInFifteenMinute;
	}

	@Override
	public List<Trade> getAllTrades() {

		List<Trade> tradeList = new ArrayList<>();

		for (String stockSymbol : tradeData.keySet()) {

			tradeList.addAll(tradeData.get(stockSymbol));

		}

		return tradeList;
	}

}
