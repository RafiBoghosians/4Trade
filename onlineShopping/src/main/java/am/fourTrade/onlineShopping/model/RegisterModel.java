package am.fourTrade.onlineShopping.model;

import java.io.Serializable;

import am.fourTrade.shoppingBackend.dto.Address;
import am.fourTrade.shoppingBackend.dto.User;

public class RegisterModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//private fields
	private User user;
	private Address billing;
	
	//Setter and Getter
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Address getBilling() {
		return billing;
	}
	public void setBilling(Address billing) {
		this.billing = billing;
	}
	
	

}
