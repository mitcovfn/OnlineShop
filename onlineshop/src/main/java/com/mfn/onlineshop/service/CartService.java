package com.mfn.onlineshop.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfn.onlineshop.dao.CartLineDAO;
import com.mfn.onlineshop.entity.Cart;
import com.mfn.onlineshop.entity.CartLine;
import com.mfn.onlineshop.model.UserModel;

@Service("cartService")
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private HttpSession session;
	
	//return Cart of the logged user
	private Cart getCart() {
		
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	
	public List<CartLine> getCartLines() {
		
		return cartLineDAO.list(this.getCart().getId());
	}
	
	

}
