package com.mfn.onlineshop.dao;

import java.util.List;

import com.mfn.onlineshop.entity.Cart;
import com.mfn.onlineshop.entity.CartLine;

public interface CartLineDAO {

	public List<CartLine> list(int cartId);

	public CartLine get(int id);

	public boolean add(CartLine cartLine);

	public boolean update(CartLine cartLine);

	public boolean remove(CartLine cartLine);

	public CartLine getByCartAndProduct(int cartId, int productId);

	public List<CartLine> listAvailable(int cartId);
	
	boolean updateCart(Cart cart);

}