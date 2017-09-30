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
	 * product.setName("Oppo Selfie 553"); product.setBrand("Oppo");
	 * product.setDescription("Description for Oppo Selfie 553");
	 * product.setUnitPrice(25000); product.setActive(true);
	 * product.setCategoryId(3); product.setSupplierId(3);
	 * 
	 * assertEquals("Something went wrong while adding products", true,
	 * productDAO.add(product));
	 * 
	 * 
	 * // read and update the product product = productDAO.get(2);
	 * product.setName("Samsung galaxy s7");
	 * assertEquals("Something went wrong while adding products", true,
	 * productDAO.update(product));
	 * 
	 * assertEquals("Something went wrong while deleting existing records", true,
	 * productDAO.delete(product));
	 * 
	 * // list assertEquals("Something went wrong while fetching list of products",
	 * 6, productDAO.list().size());
	 * 
	 * }
	 */

	@Test
	public void testListOfActiveProducts() {
		assertEquals("Something went wrong while fetching the list of products!", 5,
				productDAO.listActiveProducts().size());
	}

	@Test
	public void testListOfActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching active records", 3,
				productDAO.listActiveProductsByCategory(3).size());
		assertEquals("Something went wrong while fetching active records", 2,
				productDAO.listActiveProductsByCategory(1).size());
	}

	@Test
	public void testGetLatestActiveProducts() {
		assertEquals("Something went wrong while fetching latest active records", 3,
				productDAO.getLatestActiveProducts(3).size());
	}

}
