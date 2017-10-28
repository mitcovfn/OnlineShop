package com.mfn.onlineshop.dao;

import java.util.List;

import com.mfn.onlineshop.entity.Address;
import com.mfn.onlineshop.entity.Cart;
import com.mfn.onlineshop.entity.User;

public interface UserDAO {
	
	boolean addUser(User user);
	User getByEmail(String email);
	
	boolean addAddress(Address address);
	
	Address getBillingAddress(User user);
	List<Address> listShipingAddresses(User user);
	
	boolean updateCart(Cart cart);
	

}
