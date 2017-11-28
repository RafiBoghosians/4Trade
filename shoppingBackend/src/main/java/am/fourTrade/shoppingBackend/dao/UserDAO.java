package am.fourTrade.shoppingBackend.dao;

import am.fourTrade.shoppingBackend.dto.Address;
import am.fourTrade.shoppingBackend.dto.Cart;
import am.fourTrade.shoppingBackend.dto.User;

public interface UserDAO {
	// add new user
	boolean addUser(User user);
	
	// add new address
	boolean addAddress(Address address);
	
	// add new cart
	boolean addCart(Cart cart);

}
