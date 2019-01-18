//does static methods mean clss methods?
//reading from file File vs file reader
//should i use else statements
//what if constructor finds social is not valid
//how to verify all data has been provided for constructors
package customer;

import java.io.*;
import java.util.Scanner;


public class Customer {
	
	private Integer customerID;
	private String firstName;
	private String lastName;
	private String socialSecNum;
	private Address address;
	
	private static Integer lastCustID =0;
	
	//class (static) methods
	
	//initialize customer ID
	public static  void initializeCustomerID(String filename) {
		try {
		File idFile = new File(filename);
		Scanner inFile = new Scanner(idFile);
		lastCustID=inFile.nextInt();
		inFile.close();
		} 
		catch (FileNotFoundException e) {
		e.getMessage();	
		
		}
		
	}
	
	//overwrite filename file with latest customer id
	public static void saveLastCustomerId() {
		try {
			FileWriter idFile = new FileWriter("filename");
			idFile.write(lastCustID);
			idFile.close();
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
	
	//static method to help with SS verifications
	//removes dashes and spaces if there are any
	public static String formatSS(String socialNum) {
		int spacePos=-1;
		do {
		spacePos = socialNum.indexOf(" ");
		if(spacePos>=0) {
		socialNum=socialNum.substring(0,spacePos)+socialNum.substring(spacePos+1);
		}
		}while(spacePos>=0);
		
		//remove dashes if any
		int dashPos=-1;
		do {
		dashPos = socialNum.indexOf("-");
		if(dashPos>=0) {
		socialNum=socialNum.substring(0,dashPos)+socialNum.substring(dashPos+1);
		}
		}while(dashPos>=0);
		return socialNum;
	}
	
	//verify social is valid
	public static boolean verifySocialSecurityNumber(String socialNum) {
		
		socialNum=formatSS(socialNum);
		
		if(socialNum.length()!=9) {
			return false;
		}
		
		//if correct length
		else {
			
			//verify numeric
			try {
				int socialNumN = Integer.valueOf(socialNum);
			}
			catch(NumberFormatException e) {
				return false;
			}
			
			//if numeric verify middle
			if( Integer.valueOf(socialNum.substring(3, 5))==0) {
			return false;
			}
			//if middle good verify last good
			if(Integer.valueOf(socialNum.substring(5))==0) {
				return false;
			}
			//if last good verify beginning
			if(Integer.valueOf(socialNum.substring(0, 3))==0 || Integer.valueOf(socialNum.substring(0, 3))>=900 || socialNum.substring(0, 3).equals("666") ) {
				return false;
			}
         
			//if got here then number is valid
			return true;
			
		}
		
	}
	
	//constructor
	public Customer(String fName, String lName, String socialNum, Address add) {
	socialNum=formatSS(socialNum);
			
				
	if(verifySocialSecurityNumber(socialNum)) {
		this.socialSecNum=socialNum;
	}
	else {
		throw new RuntimeException("Invalid Social");
	}
	
	//verify all data has been given
	this.lastName=lName;
	this.firstName=fName;
	this.address=add;
	this.customerID=++lastCustID;
	
	}
	
	//alternate constructor
	public Customer (String fName, String lName, String socialNum, String street, String city, String state, String zip) {
		this(fName, lName, socialNum, new Address(street, city, state, zip));
	}
	
	//getters
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getSocialSecNum() {
		return socialSecNum;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	
	public Address getAddress() {
		return new Address(address.getStreet(),address.getCity(), address.getState().toString(),address.getZip());
	}
	
	//setters
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(Address address) {
		this.address = new Address(address.getStreet(),address.getCity(),address.getState().toString(),address.getZip());
	}
	
	public void setAddress(String street, String city, String state, String zip) {
		this.address = new Address(street, city, state, zip);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (socialSecNum == null) {
			if (other.socialSecNum != null)
				return false;
		} else if (!socialSecNum.equals(other.socialSecNum))
			return false;
		return true;
	}
	
	public int compareTo(Customer cust) {
		
		return this.socialSecNum.compareTo(cust.socialSecNum);
		
	}

	@Override
	public String toString() {
	StringBuilder output= new StringBuilder("Customer #"+customerID +":\n");
	output.append(firstName +" "+lastName+"\n");
	output.append("Social Security Number: "+socialSecNum.substring(0,3)+"-"+socialSecNum.substring(3,5)+"-"+socialSecNum.substring(5)+"\n");//insert dashes to increase readability
	output.append(address);//implicit call to toString of address class
	return output.toString();
	}

	
	
	
	

}
