package banking;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class userInterface {

	@Autowired
	private accountCreator accountCreator;
	@Autowired
	private transactionManager transactionManager;

	public userInterface(accountCreator accountCreator,
			transactionManager transactionManager) {
		this.accountCreator = accountCreator;
		this.transactionManager = transactionManager;
	}

	// user interface menu
	public void init() {

		// fields for the upcoming switch
		Scanner in = new Scanner(System.in);
		int input;
		boolean exit = false;

		// start of the UI experience
		while (exit != true) {
			System.out.println("\nPlease state your next action\n"
					+ "Enter 1 to create an account\n" + "Enter 2 to log in\n"
					+ "Enter 3 to exit\n");

			input = in.nextInt();
			switch (input) {

			// entering 1 allows you to create a new account
			case 1:
				accountCreator.accountCreate();
				break;

			// entering 2 allows you to log in to your account,
				// to view account info
				// and perform a transaction
			case 2:
				transactionManager.transactionMenu();
				break;

			// entering 3 exits
			case 3:
				exit = true;
				break;
			}
		}
	}
}
