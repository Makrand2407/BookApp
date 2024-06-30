package com.BookApp.Dao;

import java.util.List;

import com.BookApp.beans.Book;
import com.BookApp.beans.Customer;
import com.BookApp.beans.Order;

public interface CustomerDao {
	boolean customerLogin(String username, String password);
    boolean registerCustomer(Customer customer);
    List<Book> getAllBooks();
    List<Book> searchBook(String keyword);
    boolean purchaseBook(int customerId, int bookId, int quantity);
    List<Order> getOrderHistory(int customerId);
	
}
