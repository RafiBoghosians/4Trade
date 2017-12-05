package am.fourTrade.onlineShopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import am.fourTrade.onlineShopping.util.FileUploadUtility;
import am.fourTrade.onlineShopping.validator.ProductValidator;
import am.fourTrade.shoppingBackend.dao.CategoryDAO;
import am.fourTrade.shoppingBackend.dao.ProductDAO;
import am.fourTrade.shoppingBackend.dto.Category;
import am.fourTrade.shoppingBackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	//Add a logger object to log the product object for testing the value of product object.
	//This is only for our debugging purpose
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	//By default the method is GET but i explicitly show it
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {

		//name = "operation", required = false
		//For Displaying useful message to the user after make some operation on product.
		
		
		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");

		Product newProduct = new Product();

		// set some of the fields
		//newProduct.setSupplierId(0); 1 for admin
		newProduct.setSupplierId(1);
		//so if admin add a new product by default it would be true
		newProduct.setActive(true);
		//name of the product and newProduct object
		mv.addObject("product", newProduct);
		
		//By the way as soon as Product newProduct = new Product(); <<newProduct>>
		//created the code for product will generated so we can use it for our images
		//so the view can access it with the name of "product" >> manageProduct.jsp
		
		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product submitted successfully.");
			}else if(operation.equals("category")){
				mv.addObject("message", "Category submitted successfully.");
			}
		}
		
		return mv;
	}
	
	//For editing the product from Manage Products 
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		// fetch the product from database
		Product newProduct = productDAO.get(id);
		// set the fetched product from the database
		mv.addObject("product", newProduct);		
		return mv;
	}
	
	//Handling product submission
	//Return type is String because after product submission that is storing inside database
	//we are going to redirect it to another URL
	@RequestMapping(value = { "/products"}, method = RequestMethod.POST)
	//in our manageProducts.jsp, modelAttribute="product" so we put "product" in @ModelAttribute
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product modifiedProduct, BindingResult results, Model model, HttpServletRequest request) {
		
		//springframework.validation.BindingResult for error registration capabilities, allowing for a Validator(@Valid) to be applied
		// add "Model model" to pass any data to the view
		
		//for validation of image
		if(modifiedProduct.getId() == 0) {
			//we are going to validate modifiedProduct and binding the result object
			new ProductValidator().validate(modifiedProduct, results);
		}
		else{
			//we need to ensure that original file name is not empty, it means there is some content in it we want to validate it
			if(!modifiedProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(modifiedProduct, results);
			}
			
		}
		
		// check if there are any errors
		if(results.hasErrors()) {
			
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Product Submission");
			
			return "page";
		}
		
		//For debugging purpose
		logger.info(modifiedProduct.toString());
		
		// we check if id is 0(product is not available) then we add the product
		//Otherwise if the id is not 0, so we have that product and we need only update it.
		if(modifiedProduct.getId() == 0) {
			//create a new product record if id is zero
			productDAO.add(modifiedProduct);
		}
		else {
			//update the existing products
			productDAO.update(modifiedProduct);
		}
		
		
		// if it is not equal to empty string, it means there is a file which is available in the file element for upload
		if(!modifiedProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, modifiedProduct.getFile(), modifiedProduct.getCode());
		}
		
		//HttpServletRequest request
		//A HTTP multipart request is a HTTP request that HTTP clients construct to send
		//files and data over to a HTTP Server. It is commonly used by browser and HTTP
		//clients to upload files to the server.
		
		
		
		
		
		return "redirect:/manage/products?operation=product";
	}
	
	

	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		//This statement is going to fetch the product from the database
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		//Activation and de-activation based on the value of active field
		//if the product was active so we are trying to deactivate the product it means the value should be false
		//if the product was deactivate, it means the value is false and we are trying to activate it
		product.setActive(!product.isActive());
		//updating the product
		productDAO.update(product);
		
		
		return (isActive)? 
				"You have successfully deactivate the product with id " + product.getId() :
				"You have successfully activate the product with id " + product.getId();
	}
	
	//To handle category submission
	@RequestMapping(value = "/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category){
		//add new category
		categoryDAO.add(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	
	
	// For categories we are creating a method level @ModelAttribute and fetch it.
	// It is going to return list of "categories" for all the request mapping
	//we use this in manageProducts.jsp select category part
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();

	}
	
	//For adding category in manage product page
	//"category" should be the same name as we have assigned in manageProducts.jsp >> modelAttribute="category"
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}

}
