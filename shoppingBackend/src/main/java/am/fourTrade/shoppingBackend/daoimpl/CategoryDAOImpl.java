package am.fourTrade.shoppingBackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import am.fourTrade.shoppingBackend.dao.CategoryDAO;
import am.fourTrade.shoppingBackend.dto.Category;

//which will be the same name as object name that is given in the PageController
@Repository("categoryDAO") 
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> list() {
									//hibernate query language(HQL)
									// Category is the entity(Class(Category.java)) name
									// not the table name inside database
		String selectActiveCategory = "FROM Category WHERE active = :active";
	
		// Query is from hibernate query interface
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		
		query.setParameter("active", true);
				//returning list of categories which are active
		return query.getResultList();
	}

	// Get a single category based on id
	@Override
	public Category get(int id) {
		// the second argument requires a reference type, so we get get(int id) and
		// wrape with Integer class
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	// add a single category
	@Override
	public boolean add(Category category) {
		try {
			// add category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// update a single category
	@Override
	public boolean update(Category category) {
		try {
			// update category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		// just deactivate the category and then update its condition 
		category.setActive(false);
		try {
			// update category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
