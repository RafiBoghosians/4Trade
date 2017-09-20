package am.fourTrade.shoppingBackend.dao;

import java.util.List;

import am.fourTrade.shoppingBackend.dto.Category;

public interface CategoryDAO {
	
	List<Category> list();
	
	Category get(int id);

}
