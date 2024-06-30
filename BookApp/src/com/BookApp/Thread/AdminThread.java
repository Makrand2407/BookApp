package com.BookApp.Thread;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.BookApp.Exceptions.BookStoreException;
import com.BookApp.Services.AdminService;
import com.BookApp.Services.AdminServiceImpl;
import com.BookApp.beans.Book;

public class AdminThread extends Thread {
	static Scanner sc = new Scanner(System.in);
	static int choice;
	static AdminService adminService = new AdminServiceImpl();

	public AdminThread() {
		super();
	}

	public void run() {
		do {
			System.out.println("---Welcome Admin---");
			System.out.println("1. Add a new book");
			System.out.println("2. Update the details of a book");
			System.out.println("3. Remove an existing book");
			System.out.println("4. Display the list of all books");
			System.out.println("Enter 0 to exit");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				System.out.println("Enter the book title : ");
				String title = sc.nextLine();
				System.out.println("Enter the author of book : ");
				String author = sc.nextLine();
				System.out.println("Enter the genre of book : ");
				String genre = sc.nextLine();
				System.out.println("Enter the price of book : ");
				double price;
				try {
					price = sc.nextDouble();
				} catch (InputMismatchException e) {
					System.out.println("Invalid Price format");
					return;
				}
				System.out.println("Enter the quantity of book : ");
				int quantity = sc.nextInt();
				try {
					adminService.addBook(new Book(0, title, author, genre, price, quantity));
					System.out.println("Book Added Successfully");
				} catch (BookStoreException e) {
					System.out.println("Error adding book: " + e.getMessage());
				}
				break;
			case 2:
				System.out.println("Enter book id to update:");
				int bookId = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter new title:");
				String newTitle = sc.nextLine();
				System.out.println("Enter new author:");
				String newAuthor = sc.nextLine();
				System.out.println("Enter new genre:");
				String newGenre = sc.nextLine();
				System.out.println("Enter new price:");
				double newPrice = sc.nextDouble();
				sc.nextLine();
				System.out.println("Enter new quantity:");
				int newQuantity = sc.nextInt();
				try {
					Book updatedBook = new Book(bookId, newTitle, newAuthor, newGenre, newPrice, newQuantity);
					boolean updated = adminService.updatedBook(updatedBook);
					if (updated) {
						System.out.println("Book Updated Successfully");
					} else {
						System.out.println("Book not found");
					}
				} catch (BookStoreException e) {
					System.out.println("Error updating book: " + e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter book id to remove:");
				bookId = sc.nextInt();
				try {
					adminService.deleteBook(bookId);
					System.out.println("Book Deleted Successfully");
				} catch (BookStoreException e) {
					System.out.println("Error deleting book: " + e.getMessage());
				}
				break;
			case 4:
				try {
					List<Book> books = adminService.getAllBooks();
					if (!books.isEmpty()) {
						System.out.println("List of all books : ");
						for (Book b : books) {
							System.out.println(b);
						}
					} else {
						System.out.println("No books found.");
					}
				} catch (BookStoreException e) {
					System.out.println("Error displaying books: " + e.getMessage());
				}
				break;
			case 0:
				System.out.println("Exiting admin menu.");
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
		} while (choice != 0);

	}

}
