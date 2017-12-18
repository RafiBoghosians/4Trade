package am.fourTrade.onlineShopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import am.fourTrade.shoppingBackend.dao.ProductDAO;
import am.fourTrade.shoppingBackend.dto.Product;

//http://localhost:8080/onlineShopping/json/data/all/products
@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	private ProductDAO productDAO;

	@RequestMapping("/all/products")
	@ResponseBody // Automatically it will look at converter(Jackson)
				   //and add to class path and listActiveProduct will be 
					//returned in the form of JSON 
	public List<Product> getAllProducts() {

		return productDAO.listActiveProducts();
	}
	
	//Instead of sending listActiveProducts we return list of all the products since admin can see all he products
	@RequestMapping("/admin/all/products")
	@ResponseBody
	public List<Product> getAllProductsForAdmin() {

		return productDAO.list();
	}
	
	@RequestMapping("/category/{id}/products")
	@ResponseBody 
	
	public List<Product> getAllProductsByCategory(@PathVariable int id) {

		return productDAO.listActiveProductsByCategory(id);
	}

}
