//filename for initializing customer IDs


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import accounts.Accruable;
import accounts.BankAccount;
import accounts.CheckingAccount;
import checks.Check;
import customer.Address;
import customer.Customer;
import fees.Fee;
import fees.FeeType;
import interest.InterestRate;
import interest.Interval;
import staff.Teller;
import transactions.DepositType;
import transactions.Transfer;

public class Bank {

	// instance variables
	private String bankName;
	private Address bankAddress;
	private ArrayList<BankAccount> accounts;
	private ArrayList<Customer> customers;
	private HashMap<FeeType, Fee> fees;
	private ArrayList<InterestRate> rates;
	private ArrayList<Teller> tellers;
	private InterestRate currentRate;

	private Integer nextID;
	// constructor A
	public Bank(String bankName, Address add) {
		this.bankName = bankName;
		bankAddress = new Address(add.getStreet(), add.getCity(), add.getState().toString(), add.getZip());
		accounts = new ArrayList<BankAccount>();
		customers = new ArrayList<Customer>();
		fees= new HashMap<FeeType, Fee>();
		rates = new ArrayList<InterestRate>();
		tellers= new ArrayList<Teller>();
		nextID=0;
	}

	// constructor B
	public Bank(String bankName, Address add, String fileName) {
		Customer.initializeCustomerID(fileName);
		bankAddress = new Address(add.getStreet(), add.getCity(), add.getState().toString(), add.getZip());
		accounts = new ArrayList<BankAccount>();
		customers = new ArrayList<Customer>();
		fees= new HashMap<FeeType, Fee>();
		rates = new ArrayList<InterestRate>();
		tellers= new ArrayList<Teller>();
		nextID=0;
	}

	// adds customer to bank (does not automatically add bank account)
	// throws Input Data Exception if customer with same SS is already in System
	public int addCustomer(String f, String l, String ssNum, Address add) {

		// put ssnum into correct form
		ssNum = Customer.formatSS(ssNum);

		// loop through array of customers
		for (Customer cust : customers) {
			if (cust.getSocialSecNum().equals(ssNum)) {
				throw new InputDataException("Customer with that Social Security Number already in system");
			}
		}

		// creat customer and add to bank
		Customer cust = new Customer(f, l, ssNum, add);
		customers.add(cust);

		return cust.getCustomerID();
	}
	
	public int addCustomer(String f, String l, String ssNum, String str, String c, String state, String z) {
		return addCustomer(f, l, ssNum, new Address(str, c, state, z));
	}

	// opens new bank account and attaches it to specified customer
	// throws inputdata exception if no customer with that ID
	public String openAccount( Double iAmt, Integer custID) {//adjusted parameters because when created main didnt think it made sense to create account number there or ask the customer for one

		Customer cust = null;

		// match customer ID to customer in Bank
		for (Customer c : customers) {

			if (c.getCustomerID() == custID) {
				cust = c;
			}

		}

		// if no customer with that ID
		if (cust == null) {
			throw new InputDataException("Cannot create account, No customer with that ID");
		}

		BankAccount acc =new BankAccount(Integer.toString(nextID), iAmt, cust);// create a bank account with matched customer
		accounts.add(acc);
		nextID++;
		return acc.getAccountID();
	}

	// creates account for new customer by first adding custome to system
	// throws inputdataexception if customer is already in system
	public String openAccount(String aID, Double amt, String f, String l, String ss, String s, String c, String st,
			String z) {
		int cID = this.addCustomer(f, l, ss, s, c, st, z);
		 return openAccount(amt, cID);
	}

	// deposits into existing account
	// throws input data exception if deposit is negative
	// throws input data exception if no account with that ID
	public void depositCash(String accountID, Double amount) {

		BankAccount acc = findAccount(accountID);
		// if account is not in system throws exception
		if (acc == null) {
			throw new InputDataException("Cannot deposit. No account with that ID");
		}
		// if found deposits
		else {
			acc.deposit(amount, DepositType.CASH);
		}
	}
	
	
	
	
	
	
	

	// withdraws from existing account
	// returns false if withdrawal amount exceeds current balance
	// throws input exception if no accoutn with that balance
	public boolean withdraw(String accountID, Double amount) {

		BankAccount acc = findAccount(accountID);

		// if account is not in system throws exception
		if (acc == null) {
			throw new InputDataException("Cannot deposit. No account with that ID");
		}
		// if found withdra
		try {
			acc.withdraw(amount);
			return true;
		} catch (InputDataException e) {
			return false;
		}
	}
	
	public void addFee(FeeType type, double amount) {
		fees.put(type, new Fee(type, amount));
	}
	
	//throws input data exception if try to cash check on non checking account
	public void cashCheck(Check check) {
		String accountid = check.getAccountID();
		BankAccount account = findAccount(accountid);
		
		if(account instanceof CheckingAccount ) {
			((CheckingAccount) account).cashCheck(check);
		}
		else {
			throw new InputDataException("Cannot cash check, account is not of type Checking Account");
		}
		
	}
	
	public void depositCheck(Check check, String accountID ) {
		try {
			cashCheck(check);
			BankAccount account = findAccount(accountID);
			account.deposit(check.getAmount(), DepositType.CHECK);
		}
		catch(InputDataException e) {
			throw new InputDataException("cannot deposit, check not from checking account");
		}
		
	}
	
	public Fee getFee(FeeType type) {
		return fees.get(type);
	}
	
	public void  postInterest(Interval interval) {
		for(BankAccount acc: accounts) {
			if(acc instanceof Accruable) {
				if(((Accruable) acc).getInterval().equals(interval)) {
					((Accruable) acc).postInterest(currentRate, interval);
				}
			}
		}
	}
	
	public void setInterestRate(double rate) {
		currentRate= new InterestRate(rate, LocalDate.now());
		rates.add(currentRate);
	
		
	}
	
	public void transfer(String from , String to, double ammount) {
		BankAccount accountFrom = findAccount(from);
		BankAccount accountTo = findAccount(to);
		
		//make sure theres enough money in the from account to take out the ammount
		try {
			
		accountFrom.transferFrom(new Transfer(from, to, ammount));
		
		accountTo.transferTo()
		
		}
		catch(InputDataException e){
			
			throw new InputDataExcepion("Cannot transfer, not enough funds in sender account");
		}
			
			
		
		
	}
	

	// verifies actual customer is the one closing account by checking SS
	// throws input data Exception if no account with that ID
	// returns true if account was closed
	// returns false if account exists but SS doesnt match or still money in account
	public boolean closeAccount(String accountID, String socialSecurityNum) {

		// put ss num in correct form
		socialSecurityNum = Customer.formatSS(socialSecurityNum);

		// search for account with tht num
		for (BankAccount acc : accounts) {

			// if find matching account
			if (accountID.equals(acc.getAccountID())) {

				// if ssnums match
				if (acc.getCustSocialSeccNum().equals(socialSecurityNum)) {

					// if balance is zero remove and return true
					if (acc.getCurrentBalance() == 0) {
						accounts.remove(acc);
						return true;
					}

					// if ssnums match but balance is not zero
					else {
						return false;
					}

				}

				// if ssnums do not match
				else {
					return false;
				}
			}
		}

		// if no account with that num throw exception
		throw new InputDataException("Cannot remove, no account with that number");

	}

	// for use within the class so do not need to keep searching array
	// private because returns shallow copy
	// returns null if not found
	private BankAccount findAccount(String accountID) {
		for (BankAccount acc : accounts) {
			if (accountID.equals(acc.getAccountID())) {
				return acc;
			}
		}

		return null;
	}
	
	//to verify to public that account is in system returns account ID if there otherwise null
	public String pfindAccount(String accountID) {
		for (BankAccount acc : accounts) {
			if (accountID.equals(acc.getAccountID())) {
				return  acc.getAccountID();
			}
		}

		return null;
	}

	// returns null if no customer with that ID
	private Customer findCustomer(Integer cusID) {
		// search through array of customers
		for (Customer cus : customers) {

			// if find matching customer
			if (cus.getCustomerID() == (cusID)) {

				return cus;
			}
		}
		return null;
	}

	// returns null if no customer with that num
	private Customer findCustomer(String socialNum) {
		socialNum = Customer.formatSS(socialNum);
		// search through array of customers
		for (Customer cus : customers) {

			// if find matching customer
			if (cus.getSocialSecNum().equals(socialNum)) {

				return cus;
			}
		}
		return null;
	}

	// returns null if no customer with that num
	public Customer findCustomer(int custID) {

		// search through array of customers
		for (Customer cus : customers) {

			// if find matching customer
			if (cus.getCustomerID() == custID) {

				return cus;
			}
		}
		return null;
	}

	// removes customer
	// returns false if customer still has accounts and cannot remove
	// throws exception if no customer with that social
	public boolean removeCustomer(String socialNum) {

		Customer cus = findCustomer(socialNum);

		boolean has = false;

		if (cus != null) {
			for (BankAccount acc : accounts) {
				if (acc.getCustSocialSeccNum().equals(socialNum)) {
					has = true;
				}
			}
			// if all accounts are closed
			if (has == false) {
				customers.remove(cus);
				return true;
			}

			// if still has accounts open
			else {
				return false;
			}
		}

		// if no customer with that num
		throw new InputDataException("Cannot remove customer, no customer with that Social Security Number");

	}

	// returns total of all bank account balances in bank system
	public double getTotal() {
		double total = 0;
		for (BankAccount acc : accounts) {
			total += acc.getCurrentBalance();
		}
		return total;
	}

	// throws exception if no account with that ID
	public double getAccountBalance(String accountID) {
		BankAccount acc;
		acc = findAccount(accountID);
		if (acc != null) {
			return acc.getCurrentBalance();
		} else {
			throw new InputDataException("Cannot get balance no Account with that ID");
		}
	}

	// returns -1 if no acccounts for that customer
	/// throws exception if customer does not exist
	public double getCustomerBalance(Integer custID) {
		double total = 0;
		boolean exists = false;
		if (findCustomer(custID) != null) {
			for (BankAccount acc : accounts) {
				if (custID == acc.getCustID()) {
					exists = true;
					total += acc.getCurrentBalance();
				}
			}
		} else {
			throw new InputDataException();
		}
		if (exists == false) {
			return -1;
		} else {
			return total;
		}
	}

	// returns -1 if no accounts for that customer
	/// throws exception if customer does not exist
	public double getCustomerBalance(String socialSecNumber) {
		for (Customer cust : customers) {
			if (Customer.formatSS(socialSecNumber).equals(cust.getSocialSecNum())) {
				return getCustomerBalance(cust.getCustomerID());
			}
		}

		// if no customer with that social
		throw new InputDataException("No customer with that Social Security Number");
	}
	
	//return account numbers belonging to specific customers
	//empty array if  no accounts
	public ArrayList<String> getCustomerAccounts(int customerID) {
		ArrayList<String> accountNums = new ArrayList<String>();
		for(BankAccount acc: accounts) {
			if(acc.getCustID()==customerID) {
				accountNums.add(acc.getAccountID());
			}
		}
		return accountNums;
	}
	
	//to string does not display customers info and account info for confidentiality reasons
;	public String toString() {
		StringBuilder output = new StringBuilder(bankName);
		output.append("Located At:/n "+ bankAddress);
		return output.toString();
	}

	//returns string of all accounts
	public String accountListToString() {
		StringBuilder output = new StringBuilder();
		for(BankAccount acc: accounts) {
			output.append(acc);
		}
		return output.toString();
	}


	

}
