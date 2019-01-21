//2 constructors?
//customer ID getter-there is none in customer class
package accounts;
import java.time.*;
import java.util.*;

import customer.Address;
import customer.Customer;
import fees.Fee;
import homework1.InputDataException;
import transactions.Deposit;
import transactions.DepositType;
import transactions.Transaction;
import transactions.Transfer;
import transactions.Withdrawal;

public class BankAccount {
	String accountID;
	Double initialBalance;
	Double currentBalance;
	LocalDate accountOpenDate;
	Customer customer;
	ArrayList<Transaction> transactions;
	ArrayList<Fee> fees;
	
	
	public BankAccount(String accountID, Double initialBalance, Customer customer) {
	if(initialBalance<100) {
		throw new InputDataException("Cannot open an account with less than $100");
	}
	this.initialBalance=initialBalance;
	this.currentBalance=initialBalance;
	this.accountOpenDate=LocalDate.now();
	this.customer=customer;
	this.accountID=accountID;
	
	transactions= new ArrayList<Transaction>();
	fees= new ArrayList<Fee>();
	}
	
	
	//deposit money into account
	public void deposit(Double dep, DepositType type) {
		if(dep<0) {
			throw new InputDataException("Deposit cannot be less than 0");
		}
		else {
			//whatever the type just add amount to total
			currentBalance+=dep;
			
			//add Deposit to transaction array list based on type
			switch (type){
			case CASH:
				transactions.add(new Deposit(accountID, dep));
				break;
			case CHECK:
				break;
			case MIXED:
				break;
			
			}
		}
	}
	
	//withdraw money from account
	public void withdraw(Double wd) {
		if(wd<0) {
			throw new InputDataException("Withdrawal cannot be less than 0 dollars");
		}
		if(wd>currentBalance) {
			throw new InputDataException("Withdrawel amount exceeds current balance");
		}
		
		currentBalance=currentBalance-wd;
		transactions.add(new Withdrawal(accountID, wd));
		
	}

	public void addFee(Fee fee) {
		
		fees.add(fee);
		currentBalance=currentBalance-fee.getFeeAmount();
	}
	
	public Transfer transferTo(double amount, String accountID) {
		Transfer t = new Transfer(accountID, this.accountID, amount);
		
		//add to transactions for this account
		transactions.add(t);
		
		currentBalance=currentBalance+amount;
		
		return t;
		
	}
	
	public void transferFrom(Transfer transfer) {
		transactions.add(transfer);
		currentBalance=currentBalance-transfer.getTransAmount();
		
	}
	
	
//getters
	public String getAccountID() {
		return accountID;
	}

	public LocalDate getAccountOpenDate() {
		return accountOpenDate;
	}
	public Double getCurrentBalance() {
		return currentBalance;
	}
	
	public String getCustFirstName() {
		return customer.getFirstName();
	}
	
	public String getCustLastName() {
		return customer.getLastName();
	}
	
	public String getCustSocialSeccNum() {
		return customer.getSocialSecNum();
	}
	
	public int getCustID() {
		return customer.getCustomerID();
	}
	
	public Address getCustomerAddress() {
		//return deep copy of address
		return new Address(customer.getAddress().getStreet(), customer.getAddress().getCity(), customer.getAddress().getState().toString(), customer.getAddress().getZip());
	}
	
	public double getInitialBalance() {
		return initialBalance;
	}
	
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BankAccount))
			return false;
		BankAccount other = (BankAccount) obj;
		if (accountID == null) {
			if (other.accountID != null)
				return false;
		} else if (!accountID.equals(other.accountID))
			return false;
		return true;
	}
	
	public int compareTo(BankAccount bAccount) {
	return	accountID.compareTo(bAccount.accountID);
	}

	public String toString() {
		StringBuilder output = new StringBuilder("Bank Account #"+accountID);
		output.append("\nOpened On "+accountOpenDate);
		output.append("\nInitial Balance: "+initialBalance);
		output.append("\nCurrent Balance: "+currentBalance);
		output.append("\nAccount Holder Information: \n");
		output.append(customer);//implicit call to toString of customer class
		return output.toString();
	}
	
	
	
	
	
	
	

}
