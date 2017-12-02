package am.fourTrade.onlineShopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
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
	
	//User email and confirmation password validation
	public String validateUser(User user, MessageContext error) {
		//string the represent my Transition Value 
		String transitionValue = "success";
		//To validate confirmPassword(Check if password matches with confirm password)
		//if (!(user.getPassword().equals(user.getConfirmPassword()))) {
		if (user.getPassword().compareToIgnoreCase(user.getConfirmPassword()) != 0) {
			//before moving to personal page we need to add that message
			error.addMessage(new MessageBuilder()
					.error()
					.source("confirmPassword")
					.defaultText("Password doesn't match with confirm password")
					.build());
			
			//before displaying a message we should ensure that transitionValue is failure
			//on failure it will go aging to personal page
			transitionValue = "failure";
		}
		//Here we check the uniqueness of email address
		// we have "User getByEmail(String email)" function in UserDAO
		//So, if we find a user by particular email, it means that email already exists
		//what we want is a null value.Thus, if null value returned by getByEmail it means email does not exists
		if(userDAO.getByEmail(user.getEmail()) != null){
			error.addMessage(new MessageBuilder()
					.error()
					.source("email")
					.defaultText("The Email address you entered is already exists.")
					.build());
			
			transitionValue = "failure";
		}

		return transitionValue;
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
		
		//save the address
		userDAO.addAddress(billing);

		return transitionValue;
	}

}
