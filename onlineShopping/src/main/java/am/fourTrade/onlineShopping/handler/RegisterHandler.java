package am.fourTrade.onlineShopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import am.fourTrade.onlineShopping.model.RegisterModel;
import am.fourTrade.shoppingBackend.dao.UserDAO;
import am.fourTrade.shoppingBackend.dto.Address;
import am.fourTrade.shoppingBackend.dto.Cart;
import am.fourTrade.shoppingBackend.dto.User;

//To make it bean we use @Component annotation
@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDAO;
	
	//This is going to return an object of type RegisterModel
	public RegisterModel init() {
		
		return new RegisterModel();
	}
	
	/*
	 * We use the below 2 methods in our signup-flow model.
	 * On exit of each state store the flow instance inside the register model(RegisterModel.Java) 
	 */
	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}

	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}
	
	//Save all the details from confirmation page
						//parse the RegisterModel from flow scope
	public String saveAll(RegisterModel model) {
		//from signup-flow(success)
		String transitionValue = "success";
		
		//Fetch the user
		User user = model.getUser();
		//if the user is normal user(not the supplier we need to create a cart )	
		if (user.getRole().equals("USER")) {
			Cart cart = new Cart();
			//setting the cart for particular user
			cart.setUser(user);
			//setting the new for particular cart
			user.setCart(cart);
		}
		
		//Save the user
		userDAO.addUser(user);
		
		//Get the address
		Address billing = model.getBilling();
		//we are going to get our user which is already stored to have relationship between user and address
		billing.setUser(user);
		billing.setBilling(true);
		
		//sae the address
		userDAO.addAddress(billing);

		return transitionValue;
	}

}
