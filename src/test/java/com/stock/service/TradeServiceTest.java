package com.stock.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.stock.exception.StockNotFound;
import com.stock.exception.TradeNotFound;
import com.stock.model.TradeType;
import com.stock.service.impl.TradeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TradeServiceTest {

	private TradeService tradeService;

	@Before
	public void setup() {

		tradeService = new TradeServiceImpl();

	}

	@Test
	public void addTradeTest() {

		Throwable exception = null;

		String stockSymbol = "JOE";
		int quantity = 8;
		TradeType indicator = TradeType.BUY;
		double stockPrice = 43;

		try {

			tradeService.addTrade(stockSymbol, quantity, indicator, stockPrice);

		} catch (StockNotFound e) {

			exception = e;

		}

		assertFalse(exception instanceof StockNotFound);

	}

	@Test
	public void addTradeTest_stockNotFound() {

		Throwable exception = null;

		String stockSymbol = "BEER";
		int quantity = 8;
		TradeType indicator = TradeType.BUY;
		double stockPrice = 43;

		try {

			tradeService.addTrade(stockSymbol, quantity, indicator, stockPrice);

		} catch (StockNotFound e) {

			exception = e;

		}

		assertTrue(exception instanceof StockNotFound);

	}

	@Test
	public void calculateStockPriceTradeMinutesTest() {

		Throwable exception = null;

		String stockSymbol = "TEA";

		try {

			tradeService.calculateStockPriceTradeMinutes(stockSymbol);

		} catch (StockNotFound e) {

			exception = e;

		} catch (TradeNotFound e) {

			exception = e;
		}

		assertFalse(exception instanceof StockNotFound);
		assertFalse(exception instanceof TradeNotFound);

	}

	@Test
	public void calculateStockPriceTradeMinutesTest_stockNotFound() {

		Throwable exception = null;

		String stockSymbol = "BEER";

		try {

			tradeService.calculateStockPriceTradeMinutes(stockSymbol);

		} catch (StockNotFound e) {

			exception = e;

		} catch (TradeNotFound e) {

			exception = e;
		}

		assertTrue(exception instanceof StockNotFound);
		assertFalse(exception instanceof TradeNotFound);

	}

	@Test
	public void calculateStockPriceTradeMinutesTest_tradeNotFound() {

		Throwable exception = null;

		String stockSymbol = "JOE";

		try {

			tradeService.calculateStockPriceTradeMinutes(stockSymbol);

		} catch (StockNotFound e) {

			exception = e;

		} catch (TradeNotFound e) {

			exception = e;
		}

		assertFalse(exception instanceof StockNotFound);
		assertTrue(exception instanceof TradeNotFound);

	}

}
