package com.BookApp.Services;

import java.util.List;

import com.BookApp.Dao.AdminDao;
import com.BookApp.Dao.AdminDaoImpl;
import com.BookApp.beans.Book;
public class AdminServiceImpl implements AdminService {
	static AdminDao adminDao = new AdminDaoImpl();
	@Override
	public boolean adminLogin(String username, String password) {
		return adminDao.adminLogin(username, password);
	}

	@Override
	public boolean addBook(Book book) {
		return adminDao.addBook(book);
	}

	@Override
	public boolean updatedBook(Book updatedBook) {
		return adminDao.updatedBook(updatedBook);
	}

	@Override
	public List<Book> getAllBooks() {
		return adminDao.getAllBooks(); 
	}

	@Override
	public boolean deleteBook(int bookId) {
		return adminDao.deleteBook(bookId);
	}

}
