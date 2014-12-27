package banking;

//STEP 1. Import required packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class pinChecker {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/BANK";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

	// pin check
	public boolean pinCheck(String account, int pin) {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// STEP 4: Execute a query
			stmt = conn.createStatement();

			String sql = "SELECT account, pin FROM Accounts";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// Retrieve by column name
				String account2 = rs.getString("account");
				int pin2 = rs.getInt("pin");

				if (account2.equals(account)) {
					if (pin2 == pin) {
						System.out.println("Pin successful.");
						return true;
					} else {
						System.out.println("Incorrect pin.");
						return false;
					}
				}
			}
			rs.close();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
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
	}// end pin check

}
