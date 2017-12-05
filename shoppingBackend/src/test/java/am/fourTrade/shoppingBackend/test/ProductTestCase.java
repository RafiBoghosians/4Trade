package am.fourTrade.shoppingBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import am.fourTrade.shoppingBackend.dao.ProductDAO;
import am.fourTrade.shoppingBackend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;

	private static ProductDAO productDAO;

	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("am.fourTrade.shoppingBackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
	}

	/*
	 * @Test public void testCRUDProduct() { product = new Product();
	 * 
	 * product.setName("Google Selfie"); product.setBrand("AAa");
	 * product.setDescription("Description for Google Selfie AAa");
	 * product.setUnitPrice(5648005); product.setActive(true);
	 * product.setCategoryId(2); product.setSupplierId(0);
	 * 
	 * assertEquals("System failed while add a products", true,
	 * productDAO.add(product));
	 * 
	 * 
	 * // read and update the product product = productDAO.get(2);
	 * product.setName("Iphone X");
	 * assertEquals("System failed while add products", true,
	 * productDAO.update(product));
	 * 
	 * assertEquals("System failed while deleting existing records", true,
	 * productDAO.delete(product));
	 * 
	 * // list assertEquals("System failed while add while fetching list of products",6, productDAO.list().size());
	 * 
	 * 
	 * }
	 */
	
	// If we look at the database we will see 8 active products
	@Test
	public void testListOfActiveProducts() {
		assertEquals("Something went wrong while fetching the list of products!", 8,
				productDAO.listActiveProducts().size());
	}
	
	//Below Explanation is only for the first statment
	/* Category id 3 is our Mobile Category
	Check list of active products in mobile section
	So we can see 5 products in mobile section, out of which 1 is deactive
	thus we will have 4 active products */
	@Test
	public void testListOfActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching active records", 4,
				productDAO.listActiveProductsByCategory(3).size());
		assertEquals("Something went wrong while fetching active records", 4,
				productDAO.listActiveProductsByCategory(1).size());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	@Test
	public void testGetLatestActiveProducts() {
		assertEquals("Something went wrong while fetching latest active records", 3,
				productDAO.getLatestActiveProducts(3).size());
	}
*/
}
