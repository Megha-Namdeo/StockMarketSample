package com.stock.app;

import java.util.Scanner;

import com.stock.model.TradeType;
import com.stock.service.StockService;
import com.stock.service.TradeService;
import com.stock.service.impl.StockServiceImpl;
import com.stock.service.impl.TradeServiceImpl;

public class StockApplication {

	private static StockService stockService = StockServiceImpl.getInstance();

	private static TradeService tradeService = TradeServiceImpl.getInstance();

	public static void main(String[] args) {

		System.out.println("For calculating dividend yield for stock: Enter DIV ");
		System.out.println("For calculating P/E ratio for stock : Enter PE ");
		System.out.println("For storing trade values for stock : Enter TRADE ");
		System.out
				.println("For calculating Volume Weighted Stock Price based on trades in past 15 minutes : Enter VOL ");
		System.out.println("For calculating the GBCE: Enter GBCE");
		System.out.println("For exit : Enter EXIT");

		try (Scanner scanner = new Scanner(System.in)) {

			String stockSymbol;

			String stockPrice;

			String option = scanner.nextLine();

			System.out.println(option.toUpperCase());

			switch (option.toUpperCase()) {

			case "DIV":

				System.out.println("Please provide stock symbol:");

				stockSymbol = scanner.nextLine();

				System.out.println("Please provide stock price:");

				stockPrice = scanner.nextLine();

				calculateStockDividendYieldForGivenStock(stockSymbol, stockPrice);

				break;

			case "PE":

				System.out.println("Please provide stock symbol:");

				stockSymbol = scanner.nextLine();

				System.out.println("Please provide stock price:");

				stockPrice = scanner.nextLine();

				calculateStockPERatioForGivenStock(stockSymbol, stockPrice);

				break;

			case "TRADE":

				System.out.println("Please provide stock symbol:");

				stockSymbol = scanner.nextLine();

				System.out.println("Please provide quantity of shares:");

				String quantity = scanner.nextLine();

				System.out.println("Please provide BUY/SELL indicator:");

				String indicator = scanner.nextLine();

				System.out.println("Please provide trading price:");

				stockPrice = scanner.nextLine();

				addNewTradeToGivenStock(stockSymbol, quantity, indicator, stockPrice);

				break;

			case "VOL":

				System.out.println("Please provide stock symbol:");

				stockSymbol = scanner.nextLine();

				tradeService.calculateStockPriceTradeMinutes(stockSymbol);

				break;

			case "GBCE":

				tradeService.calculateGBCE();

				break;

			case "EXIT":

				System.out.println("SEE YOU AGAIN");

				break;

			default:

				System.out.println("Not a Valid Option");

				break;

			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

			e.printStackTrace();
		}

	}

	private static void addNewTradeToGivenStock(String stockSymbol, String quantity, String indicator,
			String stockPrice) throws Exception {

		tradeService.addTrade(stockSymbol, validateQuantity(quantity), validateIndicator(indicator),
				validate(stockPrice));

	}

	private static void calculateStockPERatioForGivenStock(String stockSymbol, String stockPrice) throws Exception {

		stockService.calculateStockPERatio(stockSymbol, validate(stockPrice));

	}

	private static void calculateStockDividendYieldForGivenStock(String stockSymbol, String stockPrice)
			throws Exception {

		stockService.calculateStockDividendYield(stockSymbol, validate(stockPrice));

	}

	private static int validateQuantity(String quantity) throws Exception {

		int number = 0;

		try {

			number = Integer.parseInt(quantity);

		} catch (NumberFormatException e) {

			throw new Exception("Provided quantity must be a number");

		}

		if (number <= 0) {

			throw new Exception("Provided quantity must be greated than 0");

		}

		return number;

	}

	private static double validate(String stockPrice) throws Exception {

		double price = 0;

		try {

			price = Double.parseDouble(stockPrice);

		} catch (NumberFormatException e) {

			throw new Exception("Provided Price must be a number");

		}

		if (price <= 0) {

			throw new Exception("Provided Price must be greated than 0");

		}

		return price;
	}

	private static TradeType validateIndicator(String indicator) throws Exception {

		try {

			return TradeType.valueOf(indicator.toUpperCase());

		} catch (IllegalArgumentException e) {

			throw new Exception("Provided Trade indicator must be BUY or SELL");

		}
	}

}
