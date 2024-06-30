package com.BookApp.beans;

public class Order {
	int orderId;
	int customerId;
	int bookId;
	int quantity;
	public Order() {
		super();
	}
	public Order(int orderId, int customerId, int bookId, int quantity) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.bookId = bookId;
		this.quantity = quantity;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", bookId=" + bookId + ", quantity="
				+ quantity + "]";
	}
	
}
