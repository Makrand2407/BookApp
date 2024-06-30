package com.BookApp.Services;

import java.util.List;

import com.BookApp.beans.Book;
import com.BookApp.beans.Customer;
import com.BookApp.beans.Order;
import com.BookApp.Dao.CustomerDao;
import com.BookApp.Dao.CustomerDaoImpl;

public class CustomerServiceImpl implements CustomerService{
	static CustomerDao customerDao = new CustomerDaoImpl();
	@Override
	public boolean customerLogin(String username, String password) {
		return customerDao.customerLogin(username, password);
	}

	@Override
	public boolean registerCustomer(Customer customer) {
		return customerDao.registerCustomer(customer);
	}

	@Override
	public List<Book> getAllBooks() {
		return customerDao.getAllBooks();
	}

	@Override
	public List<Book> searchBook(String keyword) {
		return customerDao.searchBook(keyword);
	}

	@Override
	public boolean purchaseBook(int customerId, int bookId, int quantity) {
		return customerDao.purchaseBook(customerId, bookId, quantity);
	}

	@Override
	public List<Order> getOrderHistory(int customerId) {
		return customerDao.getOrderHistory(customerId);
	}

}
