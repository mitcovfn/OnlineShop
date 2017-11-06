package com.mfn.onlineshop.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfn.onlineshop.dao.CartLineDAO;
import com.mfn.onlineshop.dao.ProductDAO;
import com.mfn.onlineshop.entity.Cart;
import com.mfn.onlineshop.entity.CartLine;
import com.mfn.onlineshop.entity.Product;
import com.mfn.onlineshop.model.UserModel;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartLineDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private HttpSession session;

	// return Cart of the logged user
	private Cart getCart() {

		return ((UserModel) session.getAttribute("userModel")).getCart();
	}

	public List<CartLine> getCartLines() {

		return cartLineDAO.list(this.getCart().getId());
	}

	public String updateCartLine(int cartLineId, int count) {

		CartLine cartLine = cartLineDAO.get(cartLineId);

		int oldTotal = cartLine.getTotal();

		Product product = cartLine.getProduct();

		// check if that much quantity is available or not
		if (product.getQuantity() < count) {
			return "result=error";
		}

		// update the cart line
		cartLine.setProductCount(count);
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setTotal(product.getUnitPrice() * count);
		cartLineDAO.update(cartLine);

		// update the cart
		Cart cart = this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
		cartLineDAO.updateCart(cart);

		return "result=updated";
	}

	public String deleteCartLine(int cartLineId) {

		CartLine cartLine = cartLineDAO.get(cartLineId);

		if (cartLine == null) {
			return "result=error";
		}

		// update the cart
		Cart cart = this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() - 1);
		cartLineDAO.updateCart(cart);

		// remove cart line
		cartLineDAO.remove(cartLine);

		return "result=deleted";

	}

	public String addCartLine(int productId) {

		Cart cart = this.getCart();
		String response = null;
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);

		if (cartLine == null) {
			// add a new cartLine
			cartLine = new CartLine();
			Product product = productDAO.get(productId);

			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setProductCount(1);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setAvailable(true);
			cartLine.setTotal(product.getUnitPrice());

			cartLineDAO.add(cartLine);

			// update the cart
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() + 1);
			cartLineDAO.updateCart(cart);

			response = "result=added";

		} else {
			response = "result=exist";
		}

		return response;
	}

}
