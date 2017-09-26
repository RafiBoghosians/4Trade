package am.fourTrade.shoppingBackend.dao;

import java.util.List;

import am.fourTrade.shoppingBackend.dto.Category;

public interface CategoryDAO {

	// Get a list of category
	List<Category> list();

	// Get a single category
	Category get(int id);

	// add a category
	boolean add(Category category);

	// update a category
	boolean update(Category category);

	// delete a category
	boolean delete(Category category);

}
