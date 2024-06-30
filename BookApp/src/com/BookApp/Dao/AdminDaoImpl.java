package com.BookApp.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.BookApp.Exceptions.BookStoreException;
import com.BookApp.Exceptions.InvalidCredentials;
import com.BookApp.Util.DbConnection;
import com.BookApp.beans.Book;

public class AdminDaoImpl implements AdminDao {

	@Override
	public boolean adminLogin(String username, String password) throws InvalidCredentials {
		String sql = "Select * From Admin where adminUser = ? AND adminPwd = ?";
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			return resultSet.next();
		} catch (SQLException e) {
			throw new InvalidCredentials("Please enter correct credentials "+e.getMessage());
		}
	}

	@Override
	public boolean addBook(Book book) throws BookStoreException {
		String sql = "INSERT INTO Book (title, author, genre, price, quantity) VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setString(3, book.getGenre());
			statement.setDouble(4, book.getPrice());
			statement.setInt(5, book.getQuantity());
			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			throw new BookStoreException("Error adding book: " + e.getMessage());
		}
	}

	@Override
	public boolean updatedBook(Book updatedBook) throws BookStoreException {
	    String sql = "UPDATE Book SET title = ?, author = ?, genre = ?, price = ?, quantity = ? WHERE bookId = ?";
		try (Connection connection = DbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, updatedBook.getTitle());
			statement.setString(2, updatedBook.getAuthor());
			statement.setString(3, updatedBook.getGenre());
			statement.setDouble(4, updatedBook.getPrice());
			statement.setInt(5, updatedBook.getQuantity());
			statement.setInt(6, updatedBook.getBookId());
			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			throw new BookStoreException("Error updating book: " + e.getMessage());
		}
	}

	@Override
	public List<Book> getAllBooks() throws BookStoreException {
		List<Book> books = new ArrayList<>();
		String sql = "Select * From Book";
		try (Connection connection = DbConnection.getConnection();
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(sql)) {
	            while (resultSet.next()) {
	                Book book = new Book(
	                        resultSet.getInt("bookId"),
	                        resultSet.getString("title"),
	                        resultSet.getString("author"),
	                        resultSet.getString("genre"),
	                        resultSet.getDouble("price"),
	                        resultSet.getInt("quantity")
	                );
	                books.add(book);
	            }
	        } catch (SQLException e) {
	            throw new BookStoreException("Error fetching all books: " + e.getMessage());
	        }
	        return books;
	}

	@Override
	public boolean deleteBook(int bookId) throws BookStoreException {
	    String sql = "DELETE FROM Book WHERE bookId = ?";
	    try (Connection connection = DbConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql)) {
	        statement.setInt(1, bookId);
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        throw new BookStoreException("Error deleting book: " + e.getMessage());
	    }
	}


}
