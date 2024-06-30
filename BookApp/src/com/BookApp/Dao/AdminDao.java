package com.BookApp.Dao;

import java.util.List;

import com.BookApp.beans.Book;

public interface AdminDao {
	boolean adminLogin(String username,String password);
	boolean addBook(Book book);
	boolean updatedBook(Book updatedBook);
	List<Book> getAllBooks();
	boolean deleteBook(int bookId);
}
