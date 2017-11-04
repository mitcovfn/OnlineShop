package com.mfn.onlineshop.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mfn.onlineshop.dao.CartLineDAO;
import com.mfn.onlineshop.dao.ProductDAO;
import com.mfn.onlineshop.dao.UserDAO;
import com.mfn.onlineshop.entity.Cart;
import com.mfn.onlineshop.entity.CartLine;
import com.mfn.onlineshop.entity.Product;
import com.mfn.onlineshop.entity.User;



public class CartLineTestCase {

	

	private static AnnotationConfigApplicationContext context;
	
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	
	private CartLine cartLine = null;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.mfn.onlineshop");
		context.refresh();
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
	}
	
	
	@Test
	public void testAddCartLine() {
		
		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("mitcovfn@mail.ru");		
		Cart cart = user.getCart();
		
		// fetch the product 
		Product product = productDAO.get(1);
		
		// Create a new CartLine
		cartLine = new CartLine();
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		cartLine.setProductCount(1);
		
		int oldTotal = cartLine.getTotal();
		
		cartLine.setTotal(product.getUnitPrice() * cartLine.getProductCount());
		
		cart.setCartLines(cart.getCartLines() + 1);
		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
		
		assertEquals("Failed to add the CartLine!",true, cartLineDAO.add(cartLine));
		assertEquals("Failed to update the cart!",true, cartLineDAO.updateCart(cart));
		
	}
	
	
	
//	@Test
//	public void testUpdateCartLine() {
//
//		// fetch the user and then cart of that user
//		User user = userDAO.getByEmail("absr@gmail.com");		
//		Cart cart = user.getCart();
//				
//		cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), 2);
//		
//		cartLine.setProductCount(cartLine.getProductCount() + 1);
//				
//		int oldTotal = cartLine.getTotal();
//				
//		cartLine.setTotal(cartLine.getProduct().getUnitPrice() * cartLine.getProductCount());
//		
//		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
//		
//		assertEquals("Failed to update the CartLine!",true, cartLineDAO.update(cartLine));	
//
//		
//	}
	
	
	
}