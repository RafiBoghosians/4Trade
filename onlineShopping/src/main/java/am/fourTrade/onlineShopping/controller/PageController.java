package am.fourTrade.onlineShopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import am.fourTrade.onlineShopping.exception.ProductNotFoundException;
import am.fourTrade.shoppingBackend.dao.CategoryDAO;
import am.fourTrade.shoppingBackend.dao.ProductDAO;
import am.fourTrade.shoppingBackend.dto.Category;
import am.fourTrade.shoppingBackend.dto.Product;

@Controller
public class PageController {
	/*
	 * @Repository("categoryDAO") is Autowired in PageController where we request
	 * access to categoryDAO we are not using any "new" keyword to instantiate, this
	 * will be done by Spring Framework(Dependency Injection)
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = { "/", "/home", "/index" })
	//Holder for both Model and View in the web MVC framework. 
	//Note that these are entirely distinct. 
	//This class merely holds both to make it possible for a controller to return both model and view in a single return value.
	public ModelAndView index() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		
		logger.info("Inside the PageController index method - INFO");
		logger.debug("Inside the PageController index method - DEBUG");

		// Passing the list of categories, we inject CategoryDAO with Spring IOC
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClickHome", true);
		return mv;
	}

	@RequestMapping(value = { "/about" })
	public ModelAndView about() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}

	@RequestMapping(value = { "/contact" })
	public ModelAndView contact() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}

	/*
	 * Methods which loads all the products based on category
	 */

	@RequestMapping(value = { "/show/all/products" })
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClickAllProducts", true);
		return mv;
	}
	
	//{id} is dynamic that we added in sidebar.jsp
	@RequestMapping(value = { "/show/category/{id}/products" })
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		// categoryDAO to fetch a single category
		Category category = null;	
		category = categoryDAO.get(id); //get the category by its id
		
		//3 Objects we are passing "title", "categories", "category"
		
		mv.addObject("title", category.getName());

		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());

		// passing the single category object
		mv.addObject("category", category);

		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}

	// View a single product
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable("id") int id) throws ProductNotFoundException {
		ModelAndView mv = new ModelAndView("page");
			
			Product product = productDAO.get(id);
			//if product is not available throw ProductNotFoundException
			//This is a checked exception so we need to "throws ProductNotFoundException" otherwise try/catch
			if(product == null) throw new ProductNotFoundException();
			
			// In order to increment the number of customer views
			product.setViews(product.getViews() + 1);
			
			//update the counter(count) of view as soon as someone goes to that particular product
			productDAO.update(product);
			
			mv.addObject("title", product.getName());
			mv.addObject("product", product);
			mv.addObject("userClickShowProduct", true);
			
		return mv;
	}
	
	

}
