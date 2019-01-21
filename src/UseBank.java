

import java.util.ArrayList;
import java.util.Scanner;

import customer.Address;
import customer.Customer;
import fees.FeeType;
import interest.Interval;

public class UseBank {
	static Bank ourBank;
	//static Scanner keyboard;
	public static void main(String[]args) {
		//keyboard = new Scanner(System.in);
		ourBank = new Bank("Homework Bank",new Address("1602 Ave. J", "Brooklyn", "New York", "10000"));
		boolean exit =false;
		while(exit==false) {
		menu();
		switch(userChoice()) {
		case 1: 
			addCustomer();
			break;
		case 2:
			addAccount();
			break;
		case 3:
			removeAccount();
			break;
		case 4:
			removeCustomer();
			break;
		case 5:
			manageAccount();
			break;
		case 6:
			manageCustomer();
			break;
		case 7:
			getTotalBankBalance();
			break;
		case 8:
			listAccounts();
			break;
		case 9:
			addFeeToBank();
			break;
		case 11:
			postDailyInterest();
			break;
		case 12:
			postMonthlyInterest();
		case 13:
			postYearlyInterest();
		case 10:
			setRate();
			break;
		case 14:
			exit =true;
			exit();
			break;
		default:
			System.out.println("Please enter a valid menu option.");
			break;
			
		}//end switch
		
		}//end while loop
		
	}
	
	




	//displays main menu
	static void menu() {
		System.out.println("1. Add a customer");
		System.out.println("2. Add new account");
		System.out.println("3. Close account");
		System.out.println("4. Remove customer");
		System.out.println("5. Manage account");
		System.out.println("6. Manage customer");
		System.out.println("7. Get total bank balance");
		System.out.println("8. List Accounts");
		System.out.println("9. Add fee to bank");
		System.out.println("10. Set Interest Rate");
		System.out.println("11. Post Daily Interest");
		System.out.println("12. Post Monthly Interst");
		System.out.println("13. Post Yearly Interest");
		System.out.println("14. Exit program");
	}
	
	//returns users choice of menu options
	static int userChoice(){
		Scanner keyboard = new Scanner(System.in);
		return keyboard.nextInt();
	}
	
	private static void addFeeToBank() {
		feeAdder();
		System.out.println("Fee Added");
		
	}
	
	private static void feeAdder() {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Enter fee type");
		String type= keyboard.nextLine();
		
		int pos = type.indexOf(" ");
		type = type.substring(0, pos)+type.substring(pos+1, type.length());
		
		
		System.out.println("How much will the bank charge for this?");
		double amount = keyboard.nextDouble();
		ourBank.addFee(FeeType.valueOf(type.toUpperCase()),amount);
	}
	
	private static void postDailyInterest() {
		
		ourBank.postInterest(Interval.DAILY);
		
	}
	
	private static void postMonthlyInterest() {
		
		ourBank.postInterest(Interval.MONTHLY);
		
	}
	private static void postYearlyInterest() {
	
		ourBank.postInterest(Interval.YEARLY);
		
	}
	

	private static void setRate() {
		rateSetter();
		System.out.println("New current interest rate set for bank");
		
	}

	
	
	private static void rateSetter() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the new interest rate. All rates are in percentages");
		 
		ourBank.setInterestRate(keyboard.nextDouble());
	}






	static void addCustomer() {
		System.out.println("Customer added. Customer ID: "+customerAdder());
	}
	static int customerAdder() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the first name of the customer: ");
		String fName = keyboard.nextLine();
		System.out.println("Enter the last name of the customer: ");
		String lName = keyboard.nextLine();
		boolean valid = true;
				String ssNum;
		do {
		System.out.println("Enter the Social Security number of the customer: ");
		 ssNum= keyboard.nextLine();
		if(!Customer.verifySocialSecurityNumber(ssNum)) {
			System.out.println("Invlid Social. Try again.");
			valid =false;
		}
		else {
			valid=true;
		}
		}while(valid==false);
		
		try {
			 return ourBank.addCustomer(fName, lName, ssNum, addressGetter());
			
		}
		catch(InputDataException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
	static Address addressGetter() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the street address of the customer: ");
		String street = keyboard.nextLine();
		System.out.println("Enter the city of the customer: ");
		String city = keyboard.nextLine();
		System.out.println("Enter the state of the customer: ");
		String state = keyboard.nextLine();
		System.out.println("Enter the zipcode of the customer: ");
		String zip = keyboard.nextLine();
		return new Address(street, city, state, zip);
	}
	
	static void addAccount() {
		Scanner keyboard= new Scanner(System.in);
		String ans;
		System.out.println("Is the new account for an existing customer? Y or N");
		
		ans=keyboard.next();
		int id;
		if(ans.equalsIgnoreCase("N")){
			id = customerAdder();
			accountAdder(id);
		}
		else{
			System.out.println("Enter the customer's ID number");
			id = keyboard.nextInt();
			accountAdder(id);
		}
	}
	
	
	static void accountAdder(int custID) {
		Scanner keyboard= new Scanner(System.in);
		double amt;
		
		System.out.println("What is the initial account balance?");
		amt=keyboard.nextDouble();
		try {
		
		System.out.println("Account created. Account Number is: "+ourBank.openAccount( amt, custID));
		}
		catch(InputDataException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//gets info for which account to close
	static void removeAccount() {
		Scanner keyboard= new Scanner(System.in);
		
		System.out.println("What is the account number of the account you wish to remove");
		String accountID=keyboard.nextLine();

		
		accountCloser(accountID);
	}
	
	//actually closes account
	static void accountCloser(String accountID) {
		Scanner keyboard= new Scanner(System.in);
		
		System.out.println("Please enter the customer's Social Security number to verify");
		String SS = keyboard.nextLine();
		
		boolean result;//to check if removal was successful
		try {
		 result = ourBank.closeAccount(accountID, SS);
		 if(result==true) {
			 System.out.println("Account successfully closed");
		 }
		 else {
			 System.out.println("Account could not be removed. Please verify that the Social Security number is correct. Please also note that the account balance must be zero in order to close the account");
		 }
		}
		catch(InputDataException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//asks which customer to remove
	static void removeCustomer() {
	Scanner keyboard= new Scanner(System.in);
		
		System.out.println("Please enter the customers social security number");
		String SS=keyboard.nextLine();

		
		customerRemover(SS);
	}
	
	//actually remvoves customer
	static void customerRemover(String SS) {
		try {
			boolean result =ourBank.removeCustomer(SS);
			if(result==true) {
				System.out.println("Customer successfully removed.");
			}
			else {
				System.out.println("Customer could not be removed, make sure all related accounts are closed and try again.");
			}
		}
		catch(InputDataException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	static void manageAccount() {
		Scanner keyboard= new Scanner(System.in);
		System.out.println("Enter the account number:");
		String accountNum=keyboard.nextLine();
		
		if(ourBank.pfindAccount(accountNum)!=null) {
			
			boolean exit =false;
			while(exit==false) {
				accountSubmenu();
			switch (userChoice()) {
			case 1:
				accDeposit(accountNum);
				break;
			case 2:
				accWithdraw(accountNum);
				break;
			case 3:
				accGetBalance(accountNum);
				break;
			case 4:
				accTransfer(accountNum);
				break;
			case 5:
				return;
			default:
				System.out.println("Please enter a valid menu option");
				break;
			}
			}//end while
		}
		
		//if null no account
		else {
			System.out.println("No account found with that number");
		}
		
	}
	
	private static void accTransfer(String accountNum) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the account you wish to transfer funds to");
		String accountTo = keyboard.nextLine();
		if(ourBank.pfindAccount(accountTo)!=null) {
			System.out.println("How much would you like to transfer?");
			Double amount= keyboard.nextDouble();
			try {		
			ourBank.transfer(accountNum, accountTo, amount);
			}
			catch(InputDataException e) {
				e.getMessage();
			}
		}
		else {
			System.out.println("no account with that ID. Recipients accounts must be in this bank");
		}
		
	}


	//submenu to manage account
	static void accountSubmenu() {
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. Get account balance");
		System.out.println("4. Transfer");
		System.out.println("5. Back to main menu");
	}
	
	//get info for deposit
	static void accDeposit(String accountNum) {
		Scanner keyboard= new Scanner(System.in);
		
		//get deposit amount
		System.out.println("Enter the amount of the deposit");
		double amount=keyboard.nextDouble();
		
		if(amount>=0) {
		
			depositer(accountNum, amount);
		
		}
		else {
		 System.out.println("Deposit amount cannot be negative");
		}
		
	}
	
	//actually deposits
	static void depositer(String accountNum, double amount) {
		
		try {
			
		ourBank.depositCash(accountNum, amount);
		
		System.out.println("Deposit succesful");
		
		}
		
		catch(InputDataException e) {//in case method is ever used in a dift way when account num has no yet been verified
			
		e.getMessage();	
		
		}	
	}
	
	//gets withdrawal amount
	static void accWithdraw(String accountNum) {
		Scanner keyboard = new Scanner(System.in);
		//get withdrawal amount
		System.out.println("Enter the amount you wish to withdraw");
		double amount=keyboard.nextDouble();
		
		withdrawer(accountNum, amount);
	}
	
	//acually withdraws
	static void withdrawer(String accountNum, Double amount) {
		
		try {
			boolean result =ourBank.withdraw(accountNum, amount);
			
			if(result==true) {
				System.out.println("Withdrew sucesfully.");
			}
			else {
				System.out.println("Withdrawal amount exceeds account balance");
			}
		}
		catch(InputDataException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	static void accGetBalance(String accountID) {
		try {
			System.out.println("Your acccount balance is: " +ourBank.getAccountBalance(accountID));
		}
		catch(InputDataException e) {
			System.out.println(e.getMessage());
		}
	}
	
	static void manageCustomer() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter the customer number:");
		int custNum=keyboard.nextInt();
		Customer cust =ourBank.findCustomer(custNum);
		if(cust!=null) {
			
			boolean exit =false;
			while(exit==false) {
				customerSubmenu();
			switch (userChoice()) {
			case 1:
				accountAdder(custNum);
				break;
			case 2:
				getTotalBalances(custNum);
				break;
			case 3:
				custChangeAddress(cust);
				break;
			case 4:
				removeAccount();//will ask for social for security purposes even though already in customer menu
				break;
			case 5:
				custGetAccountBalance(custNum);
				break;
			case 6:
				return;
			default:
				System.out.println("Please enter a valid menu option");
				break;
			}
			}//end while
		}
		
		//if null no account
		else {
			System.out.println("No Customer found with that ID number");
		}
	}
	
	//submenu to manage customer
	static void customerSubmenu() {
		System.out.println("1. Open an account");
		System.out.println("2. Get total balances");
		System.out.println("3. Change Address");
		System.out.println("4. Close a account");
		System.out.println("5. Get account balance");
		System.out.println("6. Main menu");
	}
	
	static void getTotalBalances(int custNum) {
		double total = ourBank.getCustomerBalance(custNum);
		if(total==-1) {
			System.out.println("Cannot get total. No accounts exist for customer");
		}
		else {
			System.out.println("Total balances: "+ total);
		}
	}
	
	static void custChangeAddress(Customer cust) {
		
		cust.setAddress(addressGetter());
		System.out.println("Address changed successfully");
		
	}
	
	static void custGetAccountBalance(int customerID) {
		Scanner keyboard = new Scanner(System.in);
		ArrayList<String> accountNums = ourBank.getCustomerAccounts(customerID);
		
		if(accountNums.get(0)==null) {
			System.out.println("Customer has no active accounts");
		}
		else {
			System.out.println("Your Accounts: "+accountNums);
			System.out.println("Please enter the account number of the account whose balance you wish to get: ");
			String num =keyboard.nextLine();
			boolean contains = false;
			for(String number: accountNums) {
				if(number.equals(num)) {
				contains= true;
				}
			}
			
			if(contains==false) {
				System.out.println("Account number is not valid or does not belong to current customer");
				return;
			}
			else {
				accGetBalance(num);
			}
			
			
		}
	}
	
	static void getTotalBankBalance() {
		System.out.println("Total balance of all accounts in bank: "+ ourBank.getTotal());
	}
	
	static void listAccounts() {
		System.out.println("Accounts: ");
		System.out.println(ourBank.accountListToString());
	}
	
	//exits program
		static void exit() {
			System.out.println("Thank you for visiting the bank.");
			System.exit(0);
		}

}
