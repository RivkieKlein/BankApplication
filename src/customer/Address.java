//to do 
//missing data exception when?

//perfect copy constructor
package customer;

public class Address {
private String street;
private String city;
private USState state;
private String zipCode;

//constructor
public Address(String street, String city, String state, String zip) {
this.street=street;
this.city=city;
int position=state.indexOf(" ");
if(position>=0) {
	state=state.substring(0, position)+state.substring(position+1);
}
this.state=USState.valueOf(state.toUpperCase());
zipCode=zip;
}

//copy constructor
public Address(Address add) {
	this(add.getStreet(), add.getCity(), add.getState().toString(), add.getZip());
}




//street getter
public String getStreet() {
	return street;
}//end getStreet

//city getter
public String getCity() {
	return city;
}//end getCity

//state getter
public USState getState() {
	return state;
}//end getState

//zip getter
public String getZip() {
	return zipCode;
}//end getZip

@Override
public String toString() {
	StringBuilder output= new StringBuilder("Address: ");
	output.append("\n "+ street+ " St.");
	output.append("\n"+ city+", "+state.getSymbol()+" "+zipCode);
	return output.toString();
}
}
