package com.BookApp.controller;

import java.util.Scanner;

import com.BookApp.Services.AdminServiceImpl;
import com.BookApp.Services.CustomerService;
import com.BookApp.Services.CustomerServiceImpl;
import com.BookApp.Thread.AdminThread;
import com.BookApp.Thread.CustomerThread;
import com.BookApp.beans.Customer;
import com.BookApp.Services.AdminService;

public class BookInterface {
	static Scanner sc = new Scanner(System.in);
	static AdminService adminService = new AdminServiceImpl();
	static CustomerService customerService = new CustomerServiceImpl();

	public static void main(String[] args) {
		int ch;
		System.out.println("Welcome to Book Store");
		do {
			System.out.println("Please select option");
			System.out.println("1.Admin Login");
			System.out.println("2.Customer Register");
			System.out.println("3.Customer Login");
			System.out.println("4.Exit");
			ch = sc.nextInt();
			sc.nextLine();

			switch (ch) {
			case 1:
				boolean adminLogin = false;
				System.out.println("--Admin Login--");
				System.out.println("Enter the username :");
				String adminUser = sc.next();
				System.out.println("Enter the password :");
				String adminPwd = sc.next();
				try {
					adminLogin = adminService.adminLogin(adminUser, adminPwd);
				} catch (Exception e) {
					System.out.println(e);
				}
				if (adminLogin) {
					System.out.println("Successfully Login");
					AdminThread at = new AdminThread();
					at.start();
					try {
						at.join();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("Invalid Credentials.");
				}
				break;
			case 2:
				System.out.println("--Customer Registration--");
				System.out.println("Enter the name :");
				String name = sc.nextLine();
				System.out.println("Enter the username :");
				String customerUser = sc.nextLine();
				System.out.println("Enter the password :");
				String customerPwd = sc.nextLine();
				try {
					customerService.registerCustomer(new Customer(0, name, customerUser, customerPwd));
					System.out.println("Succesfully Registration");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				boolean customerLogin = false;
				System.out.println("--Customer Login--");
				System.out.println("Enter the username :");
				String customerUser1 = sc.nextLine();
				System.out.println("Enter the password :");
				String customerPwd1 = sc.nextLine();
				try {
					customerLogin = customerService.customerLogin(customerUser1, customerPwd1);
				} catch (Exception e) {
					System.out.println(e);
				}
				if (customerLogin) {
					System.out.println("Successfully Login");
					CustomerThread ct = new CustomerThread();
					ct.start();
					try {
						ct.join();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				} else {
					System.out.println("Invalid Credentials.");
				}
				break;
			case 4:
				System.out.println("Thank You For Visiting");
				ch = 0;
				break;
			default:
				System.out.println("Invalid choice.Please enter valid choice.");
				break;
			}
		} while (ch != 0);
	}
}
