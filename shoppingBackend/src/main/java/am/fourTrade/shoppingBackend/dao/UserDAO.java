package am.fourTrade.shoppingBackend.dao;

import java.util.List;

import am.fourTrade.shoppingBackend.dto.Address;
import am.fourTrade.shoppingBackend.dto.Cart;
import am.fourTrade.shoppingBackend.dto.User;

public interface UserDAO {
	// add new user
	boolean addUser(User user);
	// fetch the user based on email
	User getByEmail(String email);
		
		
	// add new address
	boolean addAddress(Address address);
	//Create an address method and fetch the billing address for a particular user
	Address getBillingAddress(User user);
	// fetch list of shipping address for a user
	List<Address> listShippingAddresses(User user);
	
	
	
	// update the cart
	boolean updateCart(Cart cart);

}
