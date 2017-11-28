package am.fourTrade.shoppingBackend.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import am.fourTrade.shoppingBackend.dao.UserDAO;
import am.fourTrade.shoppingBackend.dto.Address;
import am.fourTrade.shoppingBackend.dto.Cart;
import am.fourTrade.shoppingBackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	//fetch the user based on email
	@Override
	public User getByEmail(String email) {
		//where the email is equal to the parameter that we pass
		String selectQuery = "FROM User WHERE email = :email";
		
		//using try catch block since
		// we are going to fetch that particular user with email address which is unique
		// and it is going to be used as username
		try {
			
			return sessionFactory.getCurrentSession()
					.createQuery(selectQuery, User.class) 	//based on our selectQuery on User.class
					.setParameter("email", email)			//param name is "email", the param that we pass email
					.getSingleResult();						//since we want to return a single user
															//It will thorw an exception if there are multiple result or no result
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}

}
