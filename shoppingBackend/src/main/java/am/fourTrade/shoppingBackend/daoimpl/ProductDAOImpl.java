package am.fourTrade.shoppingBackend.daoimpl;

import java.util.List;
import am.fourTrade.shoppingBackend.dao.ProductDAO;
import am.fourTrade.shoppingBackend.dto.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	// get a list of products
	@Override
	public List<Product> list() {

		return sessionFactory.getCurrentSession().createQuery("From Product", Product.class).getResultList();
	}

	// Get a single product based on id
	@Override
	public Product get(int productId) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// add a product
	@Override
	public boolean add(Product product) {
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	// update a single product
	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	//remove a product
	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);
			return this.update(product);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> listActiveProducts() {
		//the first active is my field name(private boolean active)
		// the second active is a parameter which we will set in the below setParameter("active", true)
		String selectActiveProducts = "From Product WHERE active = :active";
		return sessionFactory
				.getCurrentSession()
				.createQuery(selectActiveProducts, Product.class)
				.setParameter("active", true)
				.getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "From Product WHERE active = :active AND categoryId = :categoryId ";
		return sessionFactory
				.getCurrentSession()
				.createQuery(selectActiveProductsByCategory, Product.class)
				.setParameter("active", true)
				//the second parameter categoryId is coming from our method argument (int categoryId)
				.setParameter("categoryId", categoryId)
				.getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		String selectLatestActiveProducts = "FROM Product WHERE active = :active ORDER BY id";
		return sessionFactory
				.getCurrentSession()
				.createQuery(selectLatestActiveProducts,Product.class)
				.setParameter("active",true)
				.setFirstResult(0)
				//So if the method arguments get for example 3, then the method will bring only 3 items
				.setMaxResults(count)
				.getResultList();
	}

}
