package am.fourTrade.shoppingBackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import am.fourTrade.shoppingBackend.dao.CategoryDAO;
import am.fourTrade.shoppingBackend.dto.Category;


@Repository("categoryDAO") //which will be the same name as object name that is given in the PageController
public class CategoryDAOImpl implements CategoryDAO {

	// static list of categories for testing purpose
	private static List<Category> categories = new ArrayList<>();

	// Add some dummy data to a static list for testing purpose which
	// would be replaced later by access to record in database tables
	static {
		Category category = new Category();

		// adding first category
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is some description for Television");
		category.setImageURL("CAT_1.png");

		categories.add(category);

		
		category = new Category();

		// adding second category
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is some description for Mobile");
		category.setImageURL("CAT_2.png");

		categories.add(category);
		
		
		category = new Category();

		// adding third category
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("This is some description for Laptop");
		category.setImageURL("CAT_3.png");

		categories.add(category);
	}

	@Override
	public List<Category> list() {
		 
		return categories;
	}

	@Override
	public Category get(int id) {
		// enhanced for loop
		for(Category category : categories) {
			if(category.getId() == id)
				return category;
		}
		return null;
		 
	}

}
