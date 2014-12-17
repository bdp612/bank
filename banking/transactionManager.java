package banking;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class transactionManager {

	@Autowired
	private transaction transaction;
	@Autowired
	private nameChecker nameChecker;
	@Autowired
	private pinChecker pinChecker;
	@Autowired
	private accountViewer accountViewer;

	// transaction manager
	public transactionManager(transaction transaction, nameChecker nameChecker,
			pinChecker pinChecker, accountViewer accountViewer) {
		this.transaction = transaction;
		this.nameChecker = nameChecker;
		this.pinChecker = pinChecker;
		this.accountViewer = accountViewer;
	}

	String account;
	Scanner in = new Scanner(System.in);
	boolean used = false;
	boolean exit = false;
	int pin;

	public void transactionMenu() {


		// checks the name
		used = false;
		while (used == false) {
			System.out.println("Please enter your account name.");
			account = in.next();
			used = nameChecker.nameCheck(account);
			if (used == false) {
				System.out.println("Name does not exist.");
				return;
			}
		}

		// checks the pin
		used = false;
		while (used == false) {
			System.out.println("Please enter your pin number.");
			pin = in.nextInt();
			used = pinChecker.pinCheck(account, pin);
			if (used == false) {
				System.out.println("Pin was incorrect.");
				return;
			}
		}

		exit = false;
		while (exit != true) {
			int input;
			System.out.println("\nPlease state your next action\n"
					+ "Enter 1 to view account information\n"
					+ "Enter 2 to perform a transaction\n"
					+ "Enter 3 to exit\n");

			input = in.nextInt();
			switch (input) {

			case 1:
				accountViewer.accountView(account);
				break;
			case 2:
				// performs the transaction
				transaction.performTransaction(account);
				break;
			case 3:
				exit = true;
				break;
			}
		}
		return;
	}
}
