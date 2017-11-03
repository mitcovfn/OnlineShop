package com.mfn.onlineshop.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mfn.onlineshop.dao.UserDAO;
import com.mfn.onlineshop.entity.Address;
import com.mfn.onlineshop.entity.User;

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
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public User getByEmail(String email) {
		String query = "FROM user WHERE email = :email";
		try {
			return sessionFactory.getCurrentSession().createQuery(query, User.class).setParameter("email", email)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Address getBillingAddress(User user) {
		String query = "FROM address WHERE user = :user AND billing = :billing";
		try {
			return sessionFactory.getCurrentSession().createQuery(query, Address.class)
					.setParameter("user", user).setParameter("billing", true)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> listShipingAddresses(User user) {
		String query = "FROM address WHERE user = :user AND shipping = :shipping";
		try {
			return sessionFactory.getCurrentSession().createQuery(query, Address.class)
					.setParameter("user", user).setParameter("shipping", true)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
