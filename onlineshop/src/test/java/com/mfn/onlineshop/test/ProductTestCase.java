package com.mfn.onlineshop.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mfn.onlineshop.dao.ProductDAO;
import com.mfn.onlineshop.entity.Product;


public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.mfn.onlineshop");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");

	}

	@Test
	public void testAddCategory() {
		product = new Product();

		product.setName("iPhone 5s");
		product.setDescription("This is one of the best phone available in the market right now!");
		product.setBrand("apple");
		product.setUnitPrice(18000);
		product.setActive(true);
		product.setCategoryId(1);
		product.setQuantity(6);
		product.setSupplierId(1);

		assertEquals("Successfully added a product inside the table!", true, productDAO.add(product));

		product = new Product();

		product.setName("Laptop");
		product.setDescription("This is one of the best phone laptop in the market right now!");
		product.setBrand("apple");
		product.setUnitPrice(155000);
		product.setActive(true);
		product.setCategoryId(2);
		product.setQuantity(6);
		product.setSupplierId(2);

		assertEquals("Successfully added a product inside the table!", true, productDAO.add(product));
	}

	@Test
	public void testGetCategory() {
		product = productDAO.get(1);

		assertEquals("Successfully fetched a single product from the table!", "iPhone 5s", product.getName());
	}

	@Test
	public void testUpdateCategory() {
		product = productDAO.get(2);
		product.setName("Asus");
		product.setDescription("Asus description");
		product.setActive(true);

		assertEquals("Successfully updated product from the table!", true, productDAO.update(product));
	}

	@Test
	public void testDeleteCategory() {
		product = productDAO.get(2);

		assertEquals("Successfully deleted product from the table!", true, productDAO.delete(product));
	}

	@Test
	public void testListCategory() {

		assertEquals("Successfully listed product from the table!", 2, productDAO.list().size());
	}
	
	@Test
	public void testlistActiveProducts() {

		assertEquals("Successfully listed product from the table!", 1, productDAO.listActiveProducts().size());
	}
	
	@Test
	public void testlistActiveProductsByCategory() {

		assertEquals("Successfully listed product from the table!", 1, productDAO.listActiveProductsByCategory(1).size());
	}
	
	@Test
	public void testgetLatestActiveProducts() {

		assertEquals("Successfully listed product from the table!", 1, productDAO.getLatestActiveProducts(1).size());
	}
}
