package main.com.app;

import java.util.Scanner;

import main.com.service.StockService;
import main.com.service.impl.StockServiceImpl;

public class StockApplication {
	
	private static StockService stockService = StockServiceImpl.getInstance();

	public static void main(String[] args) {

		System.out.println("For calculating dividend yield for stock: enter DIV ");
		System.out.println("For calculating P/E ratio for stock : Enter PE ");
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

				System.out.println("Calculating dividend yield ");

				stockService.calculateStockDividendYield(stockSymbol, stockPrice);

				break;

			case "PE":

				System.out.println("Please provide stock symbol:");

				stockSymbol = scanner.nextLine();

				System.out.println("Please provide stock price:");

				stockPrice = scanner.nextLine();

				System.out.println("Calculating P/E");

				stockService.calculateStockPERatio(stockSymbol, stockPrice);

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


}
