package customer;

public enum USState {



	ALABAMA("AL"),//automatically calls private constructor this is actually a call to a constructor
	ALASKA("AK"),
	ARIZONA("AZ"),
	ARKANSAS("AR"),
	CALIFORNIA("CA"),
	COLORADO("CO"),
	CONNECTICUT("CT"),
	DELAWARE("DE"),
	FLORIDA("FL"),
	GEORGIA("GA"),
	HAWAII("HI"),
	IDAHO("ID"),
	ILLINOIS("IL"),
	INDIANA("IN"),
	IOWA("IA"),
	KANSAS("KS"),
	KENTUCKY("KY"),
	LOUISIANA("LA"),
	MAINE("ME"),
	MARYLAND("MD"),
	MASSACHUSETTS("MA"),
	MICHIGAN("MI"),
	MINNESOTA("MN"),
	MISSISSIPPI("MS"),
	MISSOURI("MO"),
	MONTANA("MT"),
	NEBRASKA("NE"),
	NEVADA("NV"),
	NEWHAMPSHIRE("NH"),
	NEWJERSEY("NJ"),
	NEWMEXICO("NM"),
	NEWYORK("NY"),
	NORTHCAROLINA("NC"),
	NORTHDAKOTA("ND"),
	OHIO("OH"),
	OKLAHOMA("OK"),
	OREGON("OR"),
	PENNSYLVANIA("PA"),
	RHODEISLAND("RI"),
	SOUTHCAROLINA("SC"),
	SOUTHDAKOTA("SD"),
	TENNESSEE("TN"),
	TEXAS("TX"),
	UTAH("UT"),
	VIRGINIA("VA"),
	VERMONT("VT"),
	WASHINGTON("WA"),
	WESTVIRGINIA("WV"),
	WISCONSIN("WI"),
	WYOMING("WY");


	private String symbol;



	private USState ( String symbol){//its a constructor but a private one. you cannot call the constructor of an enumerated type

	     this.symbol = symbol;
	}



	public String getSymbol(){// but the getter is public if anyone wants to know what symbol belongs to a state
		return symbol;
	}
}

