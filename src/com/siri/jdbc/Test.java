package com.siri.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

//Steps to Connect to DB using JDBC
//Step1: Connect to DB
//Step2: Statement class
//Step3: Result Set
public class Test {
	public static Connection mysqlconnection = null;

	public static void main(String[] args) {

		try {
			// Step1: COnnect to DB using connectToDb method
			mysqlconnection = connectToDB();	
			validateByPreparedStatemet(mysqlconnection);
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
	}

	// connect to DB method
	// Method step 1: Load the driver class
	// Method step 2: Create connection
	public static Connection connectToDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
		return connection;
	}

	public static void validateByPreparedStatemet(Connection myconnection) throws SQLException {
		PreparedStatement prepstatement = myconnection
				.prepareStatement("select * from customers where customer_username=? AND customer_password = ?");
		System.out.println("Enter your username");
		Scanner scan = new Scanner(System.in);
		String uname = scan.next();
		System.out.println("Enter your password");
		String pswd = scan.next();
		prepstatement.setString(1,uname);
		prepstatement.setString(2, pswd);
		
		ResultSet result = prepstatement.executeQuery();
		if(result.first()) {
			System.out.println("Valid user");
		}else {
			System.out.println("Invalid username and password");
		}
		scan.close();
	}
	
}