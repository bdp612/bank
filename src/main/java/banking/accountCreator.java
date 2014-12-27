package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class accountCreator {

	@Autowired
	private nameChecker nameChecker;

	public accountCreator(nameChecker nameChecker) {
		this.nameChecker = nameChecker;
	}

	boolean used = true;
	String account;
	Scanner in = new Scanner(System.in);
	int pin;
	int amount;

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/BANK";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	public void accountCreate() {

		used = true;

		// if the account name is new, move on to the pin entry
		while (used == true) {
			System.out.println("Please enter the account name.");
			account = in.next();
			used = nameChecker.nameCheck(account);
			if (used == true) {
				System.out.println("Name already in use.");
			}
		}

		// sets the pin, and the starting balance
		System.out.println("Please enter a six-digit pin number.");
		pin = in.nextInt();
		System.out.println("Please enter amount to start account with.");
		amount = in.nextInt();

		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute the update
			System.out.println("Updating records...");
			stmt = conn.createStatement();

			String sql = "INSERT INTO accounts (account, pin, amount) VALUES('"
					+ account + "','" + pin + "','" + amount + "')";
			stmt.executeUpdate(sql);
			System.out.println("Inserted records successfully...");

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}// end finally try
		}// end try

		System.out
				.println("Congratulations, you have created your new account");

	} // end account create

}
