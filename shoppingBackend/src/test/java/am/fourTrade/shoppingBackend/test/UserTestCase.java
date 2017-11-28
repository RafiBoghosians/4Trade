package am.fourTrade.shoppingBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import am.fourTrade.shoppingBackend.dao.UserDAO;
import am.fourTrade.shoppingBackend.dto.Address;
import am.fourTrade.shoppingBackend.dto.Cart;
import am.fourTrade.shoppingBackend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Address address = null;
	private Cart cart = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("am.fourTrade.shoppingBackend");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
	//testing all the add method that we have added
	@Test
	public void testAdd() {
		//Setting values
		user = new User();
		user.setFirstName("James");
		user.setLastName("Gosling");
		user.setEmail("jamesgosling@gmail.com");
		user.setContactNumber("19195562");
		user.setRole("USER");
		user.setPassword("123456");
		
		
		//Once the user is added, an id would be assigned to that particular user
		// Adding the User
		assertEquals("System failed to add a new user",true, userDAO.addUser(user)); //return type would be true
											
		address = new Address();
		address.setAddressLineOne("40 Marshal Baghramyan Ave.");
		address.setAddressLineTwo("1000 Broadway, Suite 280, Oakland, CA 94607");
		address.setCity("Yerevan");
		address.setState("Yerevan");
		address.setCountry("Armenia");
		address.setPostalCode("0051");
		//Since this will be the communication address the billing part will be true
		//And the shipping part is false
		address.setBilling(true);
		
		//Address requires userId
		//Once the above user was created we can get the id of that particular user to show the address
		//So we link the user with the address using userId
		address.setUserId(user.getId());
		
		// Add The address		
		assertEquals("System failed to add a new address",true, userDAO.addAddress(address));
		
		if(user.getRole().equals("USER")) {
			// create a new cart for the USER
			cart = new Cart();
			cart.setUser(user);

			//Add a cart for the user 
			assertEquals("System failed to add a new cart",true, userDAO.addCart(cart));
			
			
			// add a new shipping address for the USER
			address = new Address();
			address.setAddressLineOne("Teryan Street 105");
			address.setAddressLineTwo("2000 Broadway, Suite 380, Oakland, CA 94607");
			address.setCity("Yerevan");
			address.setState("Yerevan");
			address.setCountry("Armenia");
			address.setPostalCode("0020");
			// set shipping to true(billing would be false)
			address.setShipping(true);
			
			// linking with the user
			address.setUserId(user.getId());
			
			// Adjoin the shipping address
			assertEquals("System failed to add a new shipping address",true, userDAO.addAddress(address));
		
		}
						
	}

}
