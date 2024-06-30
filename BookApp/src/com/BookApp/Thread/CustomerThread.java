package com.BookApp.Thread;

import java.util.List;
import java.util.Scanner;

import com.BookApp.Exceptions.BookStoreException;
import com.BookApp.Services.CustomerService;
import com.BookApp.Services.CustomerServiceImpl;
import com.BookApp.beans.Book;
import com.BookApp.beans.Order;

public class CustomerThread extends Thread {
	static Scanner sc = new Scanner(System.in);
	static int choice;
	static CustomerService customerService = new CustomerServiceImpl();

	public CustomerThread() {
		super();
	}

	public void run() {
		do {
			System.out.println("--Welcome Customer--");
			System.out.println("1. View all books");
			System.out.println("2. Search for books");
			System.out.println("3. Purchase books");
			System.out.println("4. View order history");
			System.out.println("Enter 0 to exit");
			choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1:
				try {
					List<Book> books = customerService.getAllBooks();
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
			case 2:
				System.out.println("Enter search keyword:");
				String keyword = sc.nextLine();
				try {
					List<Book> books = customerService.searchBook(keyword);
					if (!books.isEmpty()) {
						System.out.println("Search results:");
						for (Book b : books) {
							System.out.println(b);
						}
					} else {
						System.out.println("No books found matching the search keyword.");
					}
				} catch (BookStoreException e) {
					System.out.println("Error updating book: " + e.getMessage());
				}
				break;
			case 3:
				System.out.println("Enter book id to purchase:");
				int bookId = sc.nextInt();
				System.out.println("Enter the quantity:");
				int quantity = sc.nextInt();
				System.out.println("Enter the customer Id :");
				int customerId = sc.nextInt();
				try {
					boolean success = customerService.purchaseBook(customerId, bookId, quantity);
					if (success) {
						System.out.println("Book purchased Successfully");
					} else {
						System.out.println("Purchase failed. Please try again");
					}
				} catch (BookStoreException e) {
					System.out.println("Error purchasing book: " + e.getMessage());
				}
				break;
			case 4:
				try {
					customerId = 1;
                    List<Order> orders = customerService.getOrderHistory(customerId);
                    if (!orders.isEmpty()) {
                        System.out.println("Order history:");
                        for (Order o : orders) {
                            System.out.println(o);
                        }
                    } else {
                        System.out.println("No order history found.");
                    }
                } catch (BookStoreException e) {
                    System.out.println("Error displaying books: " + e.getMessage());
                }
                break;
            case 0:
                System.out.println("Exiting.");
                break;
			default:
				System.out.println("Invalid choice");
				break;
			}
		} while (choice != 0);

	}

}
