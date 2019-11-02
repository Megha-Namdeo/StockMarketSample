package main.com.app;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import main.com.model.TradeType;
import main.com.service.StockService;
import main.com.service.TradeService;
import main.com.service.impl.StockServiceImpl;
import main.com.service.impl.TradeServiceImpl;

public class StockApplication {

	private static StockService stockService = StockServiceImpl.getInstance();

	private static TradeService tradeService = TradeServiceImpl.getInstance();

	public static void main(String[] args) {

		System.out.println("For calculating dividend yield for stock: Enter DIV ");
		System.out.println("For calculating P/E ratio for stock : Enter PE ");
		System.out.println("For storing trade values for stock : Enter TRADE ");
		System.out.println("For calculating Volume Weighted Stock Price based on trades in past 15 minutes : Enter VOL ");
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

				validate(stockPrice);

				stockService.calculateStockDividendYield(stockSymbol, stockPrice);

				break;

			case "PE":

				System.out.println("Please provide stock symbol:");

				stockSymbol = scanner.nextLine();

				System.out.println("Please provide stock price:");

				stockPrice = scanner.nextLine();

				validate(stockPrice);

				stockService.calculateStockPERatio(stockSymbol, stockPrice);

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

				validate(quantity, indicator, stockPrice);

				tradeService.addTrade(stockSymbol, quantity, indicator, stockPrice);

				break;
				
			case "VOL":
				
				System.out.println("Please provide stock symbol:");

				stockSymbol = scanner.nextLine();
				
				tradeService.calculateStockPriceTradeMinutes(stockSymbol);

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

	private static void validate(String quantity, String indicator, String stockPrice) throws Exception {

		int number = 0;

		try {

			number = Integer.parseInt(quantity);

		} catch (NumberFormatException e) {

			throw new Exception("Provided quantity must be a number");

		}

		if (number <= 0) {

			throw new Exception("Provided quantity must be greated than 0");

		}

		if (!Arrays.stream(TradeType.values()).map(TradeType::name).collect(Collectors.toSet())
				.contains(indicator.toUpperCase())) {

			throw new Exception("Provided Trade indicator must be BUY or SELL");

		}

		validate(stockPrice);

	}

	private static void validate(String stockPrice) throws Exception {

		double price = 0;

		try {

			price = Double.parseDouble(stockPrice);

		} catch (NumberFormatException e) {

			throw new Exception("Provided Price must be a number");

		}

		if (price <= 0) {

			throw new Exception("Provided Price must be greated than 0");

		}
	}

}
