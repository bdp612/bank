package banking;

//STEP 1. Import required packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class amountUpdater {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/BANK";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	// amount update
	public boolean amountUpdate(String account, int newAmount) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			stmt = conn.createStatement();

			String sql = "SELECT account, amount FROM Accounts";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String account2 = rs.getString("account");
				if (account2.equals(account)) {
					int oldAmount = rs.getInt("amount");
					newAmount = newAmount + oldAmount;
				}
			}
			
			if (newAmount < 0){
				System.out.println("Sorry, insufficient funds.");
				return false;
			}

			sql = "UPDATE Accounts SET amount = '" + newAmount
					+ "' WHERE account = '" + account + "'";
			stmt.executeUpdate(sql);

			// Now you can extract all the records
			// to see the updated records
			sql = "SELECT account, amount FROM Accounts";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// Retrieve by column name
				String account2 = rs.getString("account");
				int amount2 = rs.getInt("amount");

				if (account2.equals(account)) {
					// Display values
					System.out.println("The new balance for account "
							+ account2 + " is $" + amount2 + ".");
					return true;
				}
			}
			rs.close();
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
		return false;
	}// end amount update

}
