package banking;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class transaction {

	@Autowired
	private nameChecker nameChecker;
	@Autowired
	private amountUpdater amountUpdater;

	public transaction(nameChecker nameChecker, amountUpdater amountUpdater) {
		this.nameChecker = nameChecker;
		this.amountUpdater = amountUpdater;
	}

	// perform transaction
	public void performTransaction(String account) {

		// scanner and fields

		Scanner in = new Scanner(System.in);
		int type;
		int amt;
		System.out.println("Please enter your transaction type.\n"
				+ "1: Deposit\n" + "2. Withdrawal\n" + "3. Transfer\n");
		type = in.nextInt();
		System.out.println("Please enter amount for the transaction");
		amt = in.nextInt();

		// switch for type input, calling makeDep, makeWith, or makeTran
		// then restarting the UI

		switch (type) {

		// deposit
		case 1:
			amountUpdater.amountUpdate(account, amt);
			return;

			// withdrawal
		case 2:
			amountUpdater.amountUpdate(account, -amt);
			return;

			// transfer
		case 3:
			boolean used = false;
			String account2 = "";
			while (used == false) {
				System.out
						.println("Please enter account to transfer money to.");
				account2 = in.next();
				used = nameChecker.nameCheck(account2);
				if (used == false) {
					System.out.println("Sorry, account does not exist.");
				}
			}
			boolean funds = amountUpdater.amountUpdate(account, -amt);
			if (funds == true){
				amountUpdater.amountUpdate(account2, amt);
			}
			return;
		}
	}
}
