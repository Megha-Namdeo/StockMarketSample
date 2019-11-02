package main.com.model;

import java.util.Date;

public class Trade {

	private Stock stock;

	private Date timeStamp;

	private int quantity;

	private TradeType type;

	private double price;

	public Trade(Stock stock, Date timeStamp, int quantity, TradeType type, double price) {
		this.stock = stock;
		this.timeStamp = timeStamp;
		this.quantity = quantity;
		this.type = type;
		this.price = price;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public TradeType getType() {
		return type;
	}

	public void setType(TradeType type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
