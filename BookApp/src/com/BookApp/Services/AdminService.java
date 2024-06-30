package com.BookApp.Services;

import java.util.List;

import com.BookApp.beans.Book;

public interface AdminService {
	boolean adminLogin(String username,String password);
	boolean addBook(Book book);
	boolean updatedBook(Book updatedBook);
	List<Book> getAllBooks();
	boolean deleteBook(int bookId);
}
