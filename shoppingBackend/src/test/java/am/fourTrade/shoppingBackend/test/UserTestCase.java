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
/*	@Test
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
	}*/
	
	/*
	//For 1 to 1 mapping
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
									
		if(user.getRole().equals("USER")) {
			// create a new cart for the USER
			cart = new Cart();
			
			cart.setUser(user);
			
			// Attaching cart with the user
			user.setCart(cart);
		}
		
		// Adding the User
		assertEquals("System failed to add a new user",true, userDAO.addUser(user));  
	}*/
	
/*
	//For updating cart
	@Test
	public void testUpdateCart() {
		
		//Fetching the user by the email
		user = userDAO.getByEmail("jamesgosling@gmail.com");
		
		//Once the user is fetched we need to get the cart of the user
		cart = user.getCart();
		
		cart.setGrandTotal(242424);
		cart.setCartLines(2);
		
		assertEquals("System failed to update a cart", true, userDAO.updateCart(cart));
		
	}
/*	
	//For adding address
	@Test
	public void testAddress() {
		
	//1.We need to add a new user
		user = new User();
		user.setFirstName("James");
		user.setLastName("Gosling");
		user.setEmail("jamesgosling@gmail.com");
		user.setContactNumber("19195562");
		user.setRole("USER");
		user.setPassword("123456");		
		//add user
		assertEquals("System failed to add a new user",true, userDAO.addUser(user));  
											

	//2.We need to add a new address
		
		address = new Address();
		address.setAddressLineOne("40 Marshal Baghramyan Ave.");
		address.setAddressLineTwo("1000 Broadway, Suite 280, Oakland, CA 94607");
		address.setCity("Yerevan");
		address.setState("Yerevan");
		address.setCountry("Armenia");
		address.setPostalCode("0051");
		address.setBilling(true);
		
		// attached the user to the address, since we are already using ManyToOne annotation and we are created that user filed
		address.setUser(user);
		assertEquals("System failed to add a new address",true, userDAO.addAddress(address));  
		
	//3.We need to add the shipping address
		
		address = new Address();
		address.setAddressLineOne("Teryan Street 105");
		address.setAddressLineTwo("2000 Broadway, Suite 380, Oakland, CA 94607");
		address.setCity("Yerevan");
		address.setState("Yerevan");
		address.setCountry("Armenia");
		address.setPostalCode("0020");
		// set shipping to true
		address.setShipping(true);
		
		//attached the user to the address
		address.setUser(user);
		
		assertEquals("System failed to add a shipping address",true, userDAO.addAddress(address));  
		
	}
/*
	@Test
	public void testAddress() {
		
		user = userDAO.getByEmail("jamesgosling@gmail.com");
		
		//we are going to add shipping address
		address = new Address();
		address.setAddressLineOne("Teryan Street 110");
		address.setAddressLineTwo("2000 Broadway, Suite 380, Oakland, CA 94607");
		address.setCity("Ashtarak");
		address.setState("Gegharquniq");
		address.setCountry("Armenia");
		address.setPostalCode("0020");
		// set shipping to true
		address.setShipping(true);
		
		//attached the user to the address
		address.setUser(user);
		
		assertEquals("System failed to add a shipping address",true, userDAO.addAddress(address));
		
	}
*/		
	
	//for testing listShippingAddresses and getBillingAddress methods
	@Test
	public void testGetAddress() {
	
		user = userDAO.getByEmail("jamesgosling@gmail.com");
		//we are checking the size of the shipping address for the user which we are fetched the above line
		//At this moment we have 2 shipping address that is why size is 2
		assertEquals("System failed to get the list of address and size doesn't match!",2 ,
				userDAO.listShippingAddresses(user).size());
				
		
		//Once we get the billing address of that particular user, we are going to get the city and
		//check if it is match with Yerevan
		assertEquals("System failed to get the billing address and size doesn't match!","Yerevan",
				userDAO.getBillingAddress(user).getCity());
	
	
	}
}
