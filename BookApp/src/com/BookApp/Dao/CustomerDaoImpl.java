package com.BookApp.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.BookApp.Exceptions.BookStoreException;
import com.BookApp.Exceptions.InvalidCredentials;
import com.BookApp.Exceptions.UserAlreadyExistException;
import com.BookApp.Util.DbConnection;
import com.BookApp.beans.Book;
import com.BookApp.beans.Customer;
import com.BookApp.beans.Order;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public boolean customerLogin(String username, String password)  throws InvalidCredentials {
		String sql = "Select * From Customer where custUser = ? AND custPwd = ?";
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
	public boolean registerCustomer(Customer customer) throws UserAlreadyExistException {
		String sql = "Insert into Customer(name,custUser,custPwd) values(?,?,?)";
		try(Connection connection = DbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setString(1, customer.getName());
            statement.setString(2, customer.getUsername());
            statement.setString(3, customer.getPassword());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
		}catch(SQLException e) {
            throw new UserAlreadyExistException("Customer Already Exist: " + e.getMessage());
		}
	}

	@Override
	public List<Book> getAllBooks() throws BookStoreException {
		List<Book> books = new ArrayList<>();
		String sql = "Select * From Book";
		try(Connection connection = DbConnection.getConnection();
				Statement statement = connection.createStatement();
	             ResultSet rs = statement.executeQuery(sql)){
			while(rs.next()) {
				Book book = new Book(
						rs.getInt("bookId"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                        );
				  books.add(book);
            }
        } catch (SQLException e) {
            throw new BookStoreException("Error fetching all books: " + e.getMessage());
        }
        return books;
	}

	@Override
	public List<Book> searchBook(String keyword) throws BookStoreException{
		 String sql = "SELECT * FROM Book WHERE title LIKE ? OR author LIKE ? OR genre LIKE ?";
		 List<Book> books = new ArrayList<>();
		 try(Connection connection = DbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)){
			 String search = "%" + keyword + "%";
			 statement.setString(1, search);
			 statement.setString(2, search);
			 statement.setString(3, search);
			 try(ResultSet rs = statement.executeQuery()){
				 while(rs.next()) {
					 Book book = new Book(
	                            rs.getInt("bookId"),
	                            rs.getString("title"),
	                            rs.getString("author"),
	                            rs.getString("genre"),
	                            rs.getDouble("price"),
	                            rs.getInt("quantity")
	                    );
	                    books.add(book);
				 }
			 }
		}catch(SQLException e) {
			throw new BookStoreException(""+e.getMessage());
		}
		 return books;
	}

	@Override
	public boolean purchaseBook(int customerId, int bookId, int quantity) throws BookStoreException {
		 String sql = "INSERT INTO Orders (customerId, bookId, quantity) VALUES (?, ?, ?)";
		 try(Connection connection = DbConnection.getConnection();
				 PreparedStatement statement = connection.prepareStatement(sql)){
			 statement.setInt(1, customerId);
	            statement.setInt(2, bookId);
	            statement.setInt(3, quantity);
	            int rowsInserted = statement.executeUpdate();
	            return rowsInserted > 0;
		 }catch(SQLException e) {
			 throw new BookStoreException(""+e.getMessage());
		 }
	}

	@Override
	public List<Order> getOrderHistory(int customerId) throws BookStoreException {
		List<Order> orders = new ArrayList<>();
        String sql = "SELECT orderId, customerId, bookId, quantity FROM Orders WHERE customerId = ?";
		try(Connection connection = DbConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Order order = new Order(
                        rs.getInt("orderId"),
                        rs.getInt("customerId"),
                        rs.getInt("bookId"),
                        rs.getInt("quantity")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new BookStoreException("Error fetching all books: " + e.getMessage());
        }
        return orders;
	}

}
