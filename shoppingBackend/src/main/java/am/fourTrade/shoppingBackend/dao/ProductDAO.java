package am.fourTrade.shoppingBackend.dao;

import java.util.List;

import am.fourTrade.shoppingBackend.dto.Product;

public interface ProductDAO {

	//Get a list of products
	List<Product> list();

	//Get a single product
	Product get(int productId);

	//add a product
	boolean add(Product product);

	//update a product
	boolean update(Product product);
	
	//delete a product
	boolean delete(Product product);

	
	
	
	//Extra Methods
	
	//List of Active Products
	List<Product> listActiveProducts();
	
	//Finding the products by category
	List<Product> listActiveProductsByCategory(int categoryId);

	// for example find 10 latest product
	List<Product> getLatestActiveProducts(int count);

}
